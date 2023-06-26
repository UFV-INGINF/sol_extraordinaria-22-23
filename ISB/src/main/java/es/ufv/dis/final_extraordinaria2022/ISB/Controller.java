package es.ufv.dis.final_extraordinaria2022.ISB;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@RestController
public class Controller {

    @PostMapping(value = "/ships",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus saveShips (@RequestBody ArrayList<Spaceship> spaceshipsList) {
        Methods.saveFile(spaceshipsList);
        return HttpStatus.OK;
    }


    @GetMapping("/ships")
    public ResponseEntity<ArrayList<Spaceship>> getShips () {
        ArrayList<Spaceship> shipsList = Methods.getShips();
        return new ResponseEntity<>(shipsList, HttpStatus.OK);
    }
}
