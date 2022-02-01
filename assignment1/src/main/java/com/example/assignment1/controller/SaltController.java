package com.example.assignment1.controller;

import com.example.assignment1.excel.SaltExcel;
import com.example.assignment1.model.Salt;
import com.example.assignment1.service.SaltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/assignment1/salt")
public class SaltController {

    @Autowired
    private SaltService saltService;

    @PostMapping("/addSalt")
    public Salt addSalt(@RequestBody Salt salt) {
        return saltService.newSalt(salt);
    }

    @PostMapping("/addSalts")
    public List<Salt> addSaltList(@RequestBody List<Salt> salt) {
        return saltService.addSalts(salt);
    }


    @GetMapping("/list/salts")
    public List<Salt> getAllSalts() {
        return saltService.getSalts();
    }

    @GetMapping("/saltById/{id}")
    public Salt getSaltById(@PathVariable String id) {
        return saltService.getSaltById(id);
    }

    @GetMapping("/getSaltBySaltNameAndControl")
    public Salt getSaltBySaltNameAndControl(@PathVariable String saltName,
                                            @PathVariable boolean control){
        return saltService.getSaltBySaltNameAndControl(saltName, control);
    }

    @PutMapping("/update")
    public Salt updateSalt(@RequestBody Salt salt) {
        return saltService.updateSalt(salt);
    }

    @PutMapping("/delete/{id}")
    public String deleteSalt(@PathVariable String id) {
        return saltService.deleteSalt(id);
    }

    @RequestMapping("/addSalts")
    public String addSalts(Model model){
        Page<Salt> page = saltService.listAll();
        List<Salt> listSalts = page.getContent();
        model.addAttribute("listSalts", listSalts);

        return "index";
    }

   /* @RequestMapping("/getSaltByName")
    public String getSaltByName(Model model, String saltName){
        List<Salt> listSalts = saltService.listAll(saltName);
        model.addAttribute("listSalts", listSalts);
        model.addAttribute("saltName", saltName);

        return "index";
    }*/

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse httpServletResponse) throws IOException{
        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment: filename-salt_info.xlsx";
        httpServletResponse.setHeader(headerKey, headerValue);
        List<Salt>  saltList = saltService.findAll();
        SaltExcel saltExcel = new SaltExcel(saltList);
        saltExcel.export(httpServletResponse);

    }


}
