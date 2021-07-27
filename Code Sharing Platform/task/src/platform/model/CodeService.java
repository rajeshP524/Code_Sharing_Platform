package platform.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    //recent code UUID
    public String recentCodeId;

    //constructors
    public CodeService(){

    }

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    // getters and setters
    public CodeRepository getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    //Service methods
    public Code findById(String id){
        Optional<Code> code = codeRepository.findById(id);
        if(code.isPresent()){
            if(!code.get().isRestricted()){
                code.get().setTime(0);
                code.get().setViews(0);
                return code.get();
            }
            else{
                long remainingTime = code.get().getRemainingTime();
                int remainingViews = code.get().getRemainingViews();
                if(code.get().isStrictlyRestricted()){
                    if((remainingTime == -1) || (remainingViews == -1)){
                        codeRepository.deleteById(id);
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
                    }
                    else{
                        codeRepository.save(code.get());
                        return code.get();
                    }
                }
                else{
                    if(code.get().isTimeRestriction()){
                        if(remainingTime == -1){
                            codeRepository.deleteById(id);
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
                        }
                    }
                    if(code.get().isViewsRestriction()){
                        if(remainingViews == -1){
                            codeRepository.deleteById(id);
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
                        }
                    }
                    codeRepository.save(code.get());
                    return code.get();
                }
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found");
        }
    }

    public void save(Code newCode){


        // create new code object
        Code code = new Code();

        // generate Universally unique Id
        UUID uuid = UUID.randomUUID();

        // set Code object properties
        code.setCode(newCode.getCode());
        code.setDummyDate(LocalDateTime.now());
        code.setDate(code.getDummyDate().toString());
        code.setId(uuid.toString());
        code.setTime(newCode.getTime());
        code.setViews(newCode.getViews());
        code.setMaxTime(newCode.getTime());
        code.setMaxViews(newCode.getViews());

        if((code.getMaxTime() > 0) || (code.getMaxViews() > 0)){
            if(code.getMaxTime() > 0){
                code.setTimeRestriction(true);
            }
            else{
                code.setTimeRestriction(false);
            }

            if(code.getMaxViews() > 0){
                code.setViewsRestriction(true);
            }
            else{
                code.setViewsRestriction(false);
            }

            code.setRestricted(true);
        }
        else{
            code.setRestricted(false);
        }

        if((code.getMaxTime() > 0) && (code.getMaxViews() > 0)){
            code.setStrictlyRestricted(true);
        }
        else{
            code.setStrictlyRestricted(false);
        }

        recentCodeId = code.getId();

        // save Code object to the H2 data base
        codeRepository.save(code);
    }

    public List<Code> getLatestCodes(){
        Iterable<Code> allCodes = codeRepository.findAll();
        List<Code> notRestricted = new ArrayList<>();
        List<Code> latestCodes = new ArrayList<>();

        allCodes.forEach(code ->{
            if(!code.isRestricted()){
                notRestricted.add(code);
            }
        });

        int count = 0;
        for(int i = notRestricted.size()-1; i >= 0; i--){
            latestCodes.add(notRestricted.get(i));
            count++;
            if(count == 10) break;
        }
        return latestCodes;
    }

}
