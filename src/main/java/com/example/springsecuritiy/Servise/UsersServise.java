package com.example.springsecuritiy.Servise;

import com.example.springsecuritiy.Entity.Users;
import com.example.springsecuritiy.Payload.ApiResponsUsers;
import com.example.springsecuritiy.Payload.UsersDto;
import com.example.springsecuritiy.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServise {
    @Autowired
    UsersRepository usersRepository;
    public ApiResponsUsers addUsers(@RequestBody UsersDto usersDto) {
        boolean b = usersRepository.existsByUsername(usersDto.getUsername());
        if (b)
            return new ApiResponsUsers("bunday foydalanuvchi nomi allaqachon ro'yhatdan o'tgan", true);
        Users users=new Users();
        users.setIsm(usersDto.getIsm());
        users.setFamiliya(usersDto.getFamiliya());
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
       usersRepository.save(users);
             return new ApiResponsUsers("foydalanuvchi ma'lumotlari saqlandi",false);
    }
    public ApiResponsUsers readUsers() {
        List<Users> list=usersRepository.findAll();
        String ss="";
        for (Users users : list) {
            String[] matn=users.toString().split(", ");
            for (String s : matn) {
                if (s.indexOf("(")>0){
                    s=s.substring(s.indexOf("(")+1);
                }
                if (s.indexOf(")")>0){
                    s=s.substring(0,s.indexOf(")"));
                }
                ss+=s+"\n";
            }
            ss+="\n";
        }
        return new ApiResponsUsers(ss,true);
    }
    public ApiResponsUsers readUserid(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if (byId.isPresent()){
            List<Users> list=usersRepository.findAll();
            for (Users users : list) {
                String[] matn=users.toString().split(", ");
                String ss="";
                for (String s : matn) {
                    if (s.indexOf("(")>0){
                        s=s.substring(s.indexOf("(")+1);
                    }
                    if (s.indexOf(")")>0){
                        s=s.substring(0,s.indexOf(")"));
                    }
                    ss+=s+"\n";
                }
                if (users.getId()==id) return new ApiResponsUsers(ss,true);
            }
        }
        return new ApiResponsUsers("Bazada bunday idli dasturchi mavjud emas!",false);
    }
    public ApiResponsUsers editUser(Integer id, UsersDto usersDto){
        Optional<Users> optionalUsers=usersRepository.findById(id);
        if(optionalUsers.isPresent()){
            Users users=optionalUsers.get();
            users.setIsm(usersDto.getIsm());
            users.setFamiliya(usersDto.getFamiliya());
            users.setUsername(usersDto.getUsername());
            users.setPassword(usersDto.getPassword());

            usersRepository.save(users);
            return new ApiResponsUsers( "ma'lumot taxrirlandi",true);
        }
        return new ApiResponsUsers("bazada bunday id li xodim mavjud emas",false);
    }
    public ApiResponsUsers deletUser(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if(!byId.isPresent()) return new ApiResponsUsers(id+"-idli malumot topilmadi!.",false);
        Users users = byId.get();
        usersRepository.delete(users);
        return new ApiResponsUsers("Malumot o'chirildi!!!",true);
    }
}
