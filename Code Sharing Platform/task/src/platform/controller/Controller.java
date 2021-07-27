package platform.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.model.CodeService;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    CodeService codeService;

    public Controller(){

    }

    public Controller(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public String getCode(@PathVariable String id, Model model) {
        Code code = codeService.findById(id);
        model.addAttribute("code", code);
        if(!code.isViewsRestriction()) return "getNoViewsR";
        return "getN";
    }

    @RequestMapping(path = "/code/new", produces = "text/html", method = RequestMethod.GET)
    public String newCode(Model model){
        return "textarea";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getLatestCodes(Model model){
        List<Code> latestCodes = codeService.getLatestCodes();
        model.addAttribute("codes", latestCodes);
        return "latest";
    }

}
