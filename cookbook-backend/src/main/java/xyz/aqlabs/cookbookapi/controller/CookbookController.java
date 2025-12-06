package xyz.aqlabs.cookbookapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbookapi.models.Cookbook;
import xyz.aqlabs.cookbookapi.models.input.CreateCookbookDto;
import xyz.aqlabs.cookbookapi.models.output.CookbookDto;
import xyz.aqlabs.cookbookapi.service.CookbookService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cookbook")
public class CookbookController {

    @Autowired
    private CookbookService service;



    @PostMapping
    public ResponseEntity<Integer> createCookBook(@RequestBody CreateCookbookDto cookbook) {
        Integer status = service.createCookbook(cookbook);
        return ResponseEntity
                .ok()
                .body(status);
    }

    @PutMapping
    public ResponseEntity<Integer> updateCookbook(@RequestBody Cookbook cookbook){
        Integer status = service.updateCookbook(cookbook);
        return ResponseEntity
                .ok()
                .body(status);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteCookBookByCookBookId(
           @RequestParam("cookbookId") final int cookbookId
    ) {
        Integer status = service.deleteCookbook(cookbookId);
        return ResponseEntity
                .ok()
                .body(status);
    }


    @GetMapping("/single")
    public ResponseEntity<CookbookDto> getCookBookByUserIdAndCookBookId(
            @RequestParam("userId") final int userId,
            @RequestParam("cookbookId") final int cookbookId
    ) {
        CookbookDto dto = service.getCookbookByCookbookIdAndUserId(userId, cookbookId);
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CookbookDto>> getListOfCookBookByUserId(
            @RequestParam("userId") final int userId
    ) {
        List<CookbookDto> recipes = service.getListOfCookbooksByUserId(userId);
        return ResponseEntity
                .ok()
                .body(recipes);
    }


}
