package storage;

import model.Cask;
import model.Distillate;
import model.Distillation;

import java.util.*;

public class Storage {

    List<Cask> casks = new ArrayList<>();
    List<Distillate> distillates = new ArrayList<>();

    public Storage() {
    }

    public void addCask(Cask cask) {
        if (!casks.contains(cask)) {
            casks.add(cask);
        }
    }

    public int getDistillationCount() {
        int count = 0;
        for (Distillate distillate : distillates) {
            for (Distillation d : distillate.getDistillations()) {
                count++;
            }
        }
        return count;
    }

    public void addDistillate(Distillate distillate) {
        if (!distillates.contains(distillate)) {
            distillates.add(distillate);
        }
    }

    public int getCaskCount() {
        return casks.size();
    }
}
