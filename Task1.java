public class Task1 {

    final static String jsonCondition = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";

    static String formSQLCondition(String str) {
        String[] conds = str.replaceAll("[{}]", "").split(", ");
        StringBuilder sb = new StringBuilder();
        for (String s : conds) {
            String[] parts = s.split(":");
            if (parts[1].equals("\"null\"")) {
                continue;
            }
            sb.append(parts[0].replaceAll("\"", ""));
            sb.append(" = ");
            sb.append(parts[1]);
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(jsonCondition);
        System.out.println(formSQLCondition(jsonCondition));
    }
}
