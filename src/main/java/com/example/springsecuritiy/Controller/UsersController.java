package com.example.springsecuritiy.Controller;

import com.example.springsecuritiy.Payload.ApiResponsUsers;
import com.example.springsecuritiy.Payload.UsersDto;
import com.example.springsecuritiy.Repository.UsersRepository;
import com.example.springsecuritiy.Servise.UsersServise;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UsersController {
    @Autowired
    UsersServise usersServise;
    @PostMapping("/joylash")
    public HttpEntity<?> joylash(@RequestBody UsersDto usersDto){
        ApiResponsUsers apiResponsUsers=usersServise.addUsers(usersDto);
        return ResponseEntity.status(apiResponsUsers.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponsUsers.getXabar());
    }
    @GetMapping("/uqish")
    public HttpEntity<?>Useruqish(){
        ApiResponsUsers apiResponse=usersServise.readUsers();
        return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }

    @GetMapping("/uqishid/{id}")
    public  HttpEntity<?> Useruqishid(@PathVariable Integer id){
        ApiResponsUsers apiResponse=usersServise.readUserid(id);
        return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @PutMapping("/taxrirlash/{id}")
    public HttpEntity<?> Firmataxrirlashid(@PathVariable Integer id, @RequestBody UsersDto usersDto){
        ApiResponsUsers apiResponsUsers=usersServise.editUser(id,usersDto);
        return ResponseEntity.status(apiResponsUsers.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponsUsers.getXabar());
    }
    @DeleteMapping("/uchirish/{id}")
    public HttpEntity<?> uchirishid(@PathVariable Integer id){
        ApiResponsUsers apiResponsUsers=usersServise.deletUser(id);
        return ResponseEntity.status(apiResponsUsers.isXolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponsUsers.getXabar());
    }
}
