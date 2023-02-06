package com.example.anar.service;

import com.example.anar.domain.River;
import com.example.anar.domain.Settlement;
import com.example.anar.repository.db.RiverRepoDB;
import com.example.anar.repository.db.SettlementRepoDB;
import com.example.anar.utils.event.ChangeEventType;
import com.example.anar.utils.event.EntityChangeEvent;
import com.example.anar.utils.observer.Observable;
import com.example.anar.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service implements Observable<EntityChangeEvent> {
    private RiverRepoDB riverRepoDB;
    private SettlementRepoDB settlementRepoDB;

    private List<Observer<EntityChangeEvent>> observers = new ArrayList<>();

    public Service(RiverRepoDB riverRepoDB, SettlementRepoDB settlementRepoDB) {
        this.riverRepoDB = riverRepoDB;
        this.settlementRepoDB = settlementRepoDB;
    }

    public List<River> getAllRivers(){
        return riverRepoDB.findAll();
    }
    public List<Settlement> getAllSettlements(){
        return settlementRepoDB.findAll();
    }

    public List<Settlement> getMediumRiskSettlements(){
        List<River> rivers = getAllRivers();
        List<Settlement> settlements = getAllSettlements();
        List<Settlement> mediumRiskSettlements = new ArrayList<>();
        for(Settlement sett: settlements){
            for(River river: rivers){
                if(river.getMean() >= sett.getCmdr() && river.getMean() < sett.getCma()){
                    mediumRiskSettlements.add(sett);
                }
            }
        }
        return mediumRiskSettlements;
    }

    public List<Settlement> getHighRiskSettlements(){
        List<River> rivers = getAllRivers();
        List<Settlement> settlements = getAllSettlements();
        List<Settlement> highRiskSettlements = new ArrayList<>();
        for(Settlement sett: settlements){
            for(River river: rivers){
                if(river.getMean() > sett.getCmdr() && river.getMean() >= sett.getCma()){
                    highRiskSettlements.add(sett);
                }
            }
        }
        return highRiskSettlements;
    }

    public List<Settlement> getLowRiskSettlements(){
        List<River> rivers = getAllRivers();
        List<Settlement> settlements = getAllSettlements();
        List<Settlement> lowRiskSettlements = new ArrayList<>();
        for(Settlement sett: settlements){
            for(River river: rivers){
                if(river.getMean() < sett.getCmdr() && river.getMean() <= sett.getCma()){
                    lowRiskSettlements.add(sett);
                }
            }
        }
        return lowRiskSettlements;
    }

    public void updateRiver(River river, int mean){
        for(River r: getAllRivers()){
            if(Objects.equals(r.getId(), river.getId())){
                r.setMean(mean);
                riverRepoDB.update(r);
            }
        }
        this.notifyObserver(new EntityChangeEvent(ChangeEventType.UPDATE, river));
    }

    @Override
    public void addObserver(Observer<EntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void notifyObserver(EntityChangeEvent t) {
        observers.forEach(x-> x.update(t));
    }
}
