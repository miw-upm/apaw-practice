package es.upm.miw.apaw_practice.domain.models.university;

public interface DegreeBuilders {
    interface Code {
        Capacity code(int code);
    }

    interface Capacity {
        KnowledgeArea capacity(int capacity);
    }

    interface KnowledgeArea {
        Description knowledgeArea(String knowledgeArea);
    }

    interface Description {
        Builder description(String description);
    }

    interface Builder {
        Degree build();
    }
}