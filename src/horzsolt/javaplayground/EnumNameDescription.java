package horzsolt.javaplayground;

public class EnumNameDescription {

    public static void main(String[] args) {

        System.out.println(new horzsolt.javaplayground.Entitle.Builder()
                .setName(Entitlement.AIG_AWS.name)
                .setDescription(Entitlement.AIG_AWS.description)
                .build().toString());

    }

    public enum Entitlement {
        AIG_AWS("AIG_AWS", "Entitlement for creating clusters using AWS Auto Scaling Groups."),
        AIG_AZURE("AIG_AZURE", "Entitlement for creating clusters using Azure Auto Scaling Groups.");

        private String name;
        private String description;

        Entitlement(final String name, final String description) {
            this.name = name;
            this.description = description;
        }
    }
}
