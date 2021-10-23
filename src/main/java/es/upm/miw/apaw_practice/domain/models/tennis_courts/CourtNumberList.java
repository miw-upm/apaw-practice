package es.upm.miw.apaw_practice.domain.models.tennis_courts;

import java.util.ArrayList;
import java.util.List;

public class CourtNumberList {

    private List<Integer> numbers;

    public CourtNumberList(){
        this.numbers = new ArrayList<>();
    }

    public CourtNumberList(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
