package com.ufv.extraordinaria.dis;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */

    ArrayList<Spaceship> spaceshipArrayList = new ArrayList<Spaceship>();

    public MainView(@Autowired SWService service) throws URISyntaxException, IOException, InterruptedException {


        // Use TextField for standard text input
        TextField textField = new TextField("Page");
        textField.addThemeName("bordered");

        Grid<Spaceship> grid = new Grid<>();
        grid.setItems(spaceshipArrayList);
        grid.addColumn(Spaceship::getName).setHeader("Name");
        grid.addColumn(Spaceship::getModel)
                .setHeader("Model");
        grid.addColumn(Spaceship::getManufacturer).setHeader("Manufacturer");
        grid.addColumn(Spaceship::getLength)
                .setHeader("Length");
        grid.addColumn(Spaceship::getCrew)
                .setHeader("Crew");
        grid.addColumn(
                new NativeButtonRenderer<>("Remove item",
                        clickedItem -> {
                            // Llamada al método de eliminar
                            ArrayList<Spaceship> listWithoutDeleted = Methods.deleteItems(spaceshipArrayList, clickedItem);
                            spaceshipArrayList = listWithoutDeleted;
                            grid.setItems(listWithoutDeleted);

                        })
        );

        Button getSpaceships = new Button("Obtener naves",
                e -> {
                    if (textField.getValue().isEmpty() || textField.getValue() == null) {
                        spaceshipArrayList.addAll(service.getShips());
                        grid.getDataProvider().refreshAll();

                    } else {
                        int number = Integer.parseInt(textField.getValue());
                        if (number > 4 || number < 1) {
                            Notification.show("Número de página no está en rango establecido");
                            return;
                        }
                        int page = Integer.parseInt(textField.getValue());
                        try {
                            ArrayList<Spaceship> ships = service.getShips(page);
                            spaceshipArrayList.addAll(ships);
                            grid.getDataProvider().refreshAll();
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

        Button saveData = new Button("Guardar",
                e -> {
                    service.saveShips(spaceshipArrayList);
                });


        // Button click listeners can be defined as lambda expressions
        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        getSpaceships.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        getSpaceships.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
//        addClassName("centered-content");

        add(textField, getSpaceships, grid, saveData);
    }

}
