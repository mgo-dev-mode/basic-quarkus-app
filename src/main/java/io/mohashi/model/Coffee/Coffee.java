package io.mohashi.model.Coffee;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Coffee {
    public Long id;
    public String name;
    public String description;
    public Long amountPerBatch;

    public static Builder newCoffee() {
        return new Builder();
    }

    public Coffee() {
    }

    public Coffee(Long id, String name, String description, Long amountPerBatch) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amountPerBatch = amountPerBatch;
    }

    public static class Builder {
        public Long id;
        public String name;
        public String description;
        public Long amountPerBatch;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder describption(String description) {
            this.description = description;
            return this;
        }

        public Builder amountByBatch(Long amountPerBatch) {
            this.amountPerBatch = amountPerBatch;
            return this;
        }

        public Coffee build() {
            return new Coffee(id, name, description, amountPerBatch);
        }
    }
}
