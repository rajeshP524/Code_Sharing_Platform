package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import platform.model.Code;
import platform.model.CodeService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    CodeService codeService;

    public RestController(){

    }

    public RestController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/api/code/{id}", produces = "application/json")
    public Code getApiCode(@PathVariable String id) {
        Code code = codeService.findById(id);
        return code;
    }

    @PostMapping(path = "/api/code/new", consumes = "application/json", produces = "application/json")
    public String addApiCode(@RequestBody Code newCode){
        codeService.save(newCode);
        String s = "{ \"id\" : \"" + codeService.recentCodeId + "\" }";
        return s;
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json")
    public Object[] getApiLatestCodes(){
        List<Code> latestCodes = codeService.getLatestCodes();
        return latestCodes.toArray();
    }
}
