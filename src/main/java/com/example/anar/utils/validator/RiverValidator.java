package com.example.anar.utils.validator;

import com.example.anar.domain.River;

public class RiverValidator implements Validator<River>{
    @Override
    public void validate(River river) {
        String errors = "";
        if(river.getName() == null){
            errors += "Name can't be null!\n";
        }
        if(river.getMean() < 0){
            errors += "Invalid mean!\n";
        }
        if(!errors.isEmpty()){
            throw new ValidatorExcept(errors);
        }
    }
}
