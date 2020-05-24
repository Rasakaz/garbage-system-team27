package com.team27.garbageSystem;

import com.team27.garbageSystem.Entities.GarbageBin;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Garbage_FIle_Tester {

    private List<GarbageBin> garbageFile;
    private String State;


    public Garbage_FIle_Tester(){
        this.garbageFile = ParseBinsAndAddToDB();
        this.State="Empty";
    }

    private List<GarbageBin> ParseBinsAndAddToDB() {
        try{
            List<GarbageBin> bins = new ArrayList<GarbageBin>();
            List<String> lines = Files.readAllLines(Paths.get("src//main//resources//static//garbage-bins.csv"));
            for(int i = 1; i < lines.size(); i++){
                String [] result = lines.get(i).split(",");
                bins.add(new GarbageBin(i, Double.parseDouble(result[2]), Double.parseDouble(result[3]),this.State));
            }
            return bins;
        } catch(Exception er){
            System.out.println(er.getMessage());
        }
        return null;
    }


    public List<GarbageBin> getGarbageFile() {
        return garbageFile;
    }
}
