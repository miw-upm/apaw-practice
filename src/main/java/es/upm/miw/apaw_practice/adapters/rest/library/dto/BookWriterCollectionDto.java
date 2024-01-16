package es.upm.miw.apaw_practice.adapters.rest.library.dto;

import java.util.List;

public class BookWriterCollectionDto {
    private List<String> namesOfBookWriter;

    public List<String> getNamesOfBookWriter() {
        return namesOfBookWriter;
    }

    public void setNamesOfBookWriter(List<String> namesOfBookWriter) {
        this.namesOfBookWriter = namesOfBookWriter;
    }

    @Override
    public String toString() {
        return "BookWriterCollectionDto{" +
                "namesOfBookWriter=" + namesOfBookWriter +
                '}';
    }
}
