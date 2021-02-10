package horzsolt.javaplayground;

public class Entitle {
    private final String name;
    private final String description;

    private Entitle(String name,
                    String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Entitle{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String description;

        public Builder() {

        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Entitle build() {
            return new Entitle(name, description);
        }
    }
}
