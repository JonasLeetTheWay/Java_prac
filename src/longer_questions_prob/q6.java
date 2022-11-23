package longer_questions_prob;

import java.util.Arrays;
import java.util.Objects;


/*
MIC Number format: YYMMDD-PB-###G
    The first six digits YYMMDD indicate the person's Birthdate in the ISO 8601:2000 format.
    That is,
    YY: Birth Year
    MM: Birth Month
    DD: Birth Day

    In this question, you will first receive several MIC Numbers, and then you are required to sort
    them in ascending order according to the three priorities given.
    Moreover, the sorting priorities will only be based on Birthdate, Birth Year, Birth Month,
    Birth Day, Gender with Male first, or Gender with Female first.
    For example, if the received priorities are, in sequence, (i) Birth Month, (ii) Gender with
    Female first, (iii) Birthdate. Then the sequence should be sorted in an ascending order by (i)
    Birth Month; (ii) if same Birth Month, then sorted by Gender with Female first. (iii) if same
    Birth Month and gender, then sorted by Birthdate.
    Please note also the oldest age is 80 years old among all the MIC Numbers received

Write a programme to

    Input, in sequence,
    • A string that contains a number of MIC Numbers; whereby a semicolon (;) is used to
    separate an MIC Number from the next MIC Number.
    • Three priorities to sort the above MIC Numbers.

    Output, in sequence, the sorted (in an ascending order) MIC Numbers based on the three
    priorities given. Each of the outputs should follow the format of
    MIC Number <space> Birth Day with two digits <space> Birth Month in English <space>
    Birth Year in 4 digits <space> Gender
    For example, a valid output should have the following format:
    790129-02-5810 29 January 1979 Female
 */

public class q6 {
/*
    static class IDComparator implements Comparator<ID> {
        int data_1 = 123;

        @Override
        public int compare(ID a, ID b) {
            int c;
            c = Integer.compare(a.getMonth(), b.getMonth());
            if (c != 0) return c;
            int a_val = a.isFemale() ? 0 : 1;
            int b_val = b.isFemale() ? 0 : 1;
            c = Integer.compare(a_val, b_val);
            //

        if (a.isFemale() == b.isFemale()) c = 0;
        else if (a.isFemale()) c = -1;
        else c = 1;

            if (c != 0) return c;
            c = Integer.compare(a.getBirthdate_asInt(), b.getBirthdate_asInt());
            return c;
        }
    }
    */

    // class for solv_with_datastructure_prob.q6, for easier access to various unique datasets at the same time
    static class ID {

        private String ID;
        private String generalInfo;

        private String gender;
        private int genderID; // 0:female, 1:male

        private String birthdate;
        private int year;
        private int month;
        private int day;

        private final String[] gender_set = {"Female", "Male"};

        private final String[] months_set = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        private int score = 0;

        ID(String ID_digit) {
            ID = ID_digit.replaceAll("\\s+", "");
            extractInfo();
        }

        private void extractInfo() {

            birthdate = ID.split("-", 6)[0];
            genderID = ID.split("-", 6)[2].charAt(3);

            final int year_digit = Integer.parseInt(birthdate.substring(0, 2));
            month = Integer.parseInt(birthdate.substring(2, 4));
            day = Integer.parseInt(birthdate.substring(4, 6));

            // formulate GenderString
            if (Integer.parseInt(String.valueOf(genderID)) % 2 == 0) {
                gender = gender_set[0];
            } else {
                gender = gender_set[1];
            }

            // formulate Year
            if (year_digit >= 30) {
                year = Integer.parseInt("19" + birthdate.substring(0, 2));
            } else {
                year = Integer.parseInt("20" + birthdate.substring(0, 2));
            }

            // formulate MonthString
            String month_str = months_set[month - 1];
            // formulate GeneralInfoString
            generalInfo = ID + " " + day + " " + month_str + " " + year + " " + gender;
        }

        public String getGeneralInfo() {
            return generalInfo;
        }

        public String getGender() {
            return gender;
        }

        public boolean isMale() {
            return Objects.equals(gender, "Male");
        }

        public boolean isFemale() {
            return Objects.equals(gender, "Female");
        }

        public String getBirthdate_asString() {
            return year + birthdate.substring(2, 6);
        }

        public int getBirthdate_asInt() {
            return Integer.parseInt(year + birthdate.substring(2, 6));
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public void addScore(int score_magnitude) {
            score += score_magnitude;
        }

        public void minusScore(int score_magnitude) {
            score -= score_magnitude;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return generalInfo;
        }

    }
    static ID[] inputIDs(String IDs) {

        String[] ID_sets = IDs.replaceAll("\\s+", "").split(";", 14);
        ID[] ID_objs = new ID[ID_sets.length];

        for (int i = 0; i < ID_sets.length; i++) {
            ID_objs[i] = new ID(ID_sets[i]);
        }

        return ID_objs;
    }


    static void run(String IDs, String... sortPrioTop3) {

        String[] sortPrio = {
                "Gender with Female first", "Gender with Male first",
                "Birth Day", "Birth Month", "Birth Year", "Birthdate"
        };
        String[] sortPrioSelected = new String[3]; // only 3

        ID[] id_objs = inputIDs(IDs);
        // debug purpose
        for (ID id : id_objs) {
            System.out.println(id);
        }

        //Arrays.sort(id_objs, solv_with_datastructure_prob.q6::compare);

//        Arrays.sort(id_objs, new Comparator<ID>() {
//            @Override
//            public int compare(ID o1, ID o2) {
//                solv_with_datastructure_prob.q6.compare(x, y);
//            }
//        });
        //Arrays.sort(id_objs, new IDComparator());

        q6_sortIDs(id_objs, sortPrioTop3);

        System.out.println("Final result: ");
        for (ID id : id_objs) {
            System.out.printf("id: %s \n", id);
        }

        //Arrays.sort(id_objs, (x, y) -> compare(x, y, data_1, data_2));

    }

    static ID[] q6_sortIDs(ID[] id_objs, String... sortPrioTop3) {

        // for loop sorting
//        for (int i = 0; i < id_objs.length-1; i++) {
//
//            ID id_curr = id_objs[i];
//            ID id_next = id_objs[i+1];

        Arrays.sort(id_objs, (x, y) -> {
            x.score = 0;
            y.score = 0;
            for (int j = 0; j < sortPrioTop3.length; j++) {
                String sortP_sel = sortPrioTop3[j];
//                int point = (int) Math.pow(10, (sortPrioTop3.length - j - 1)); // score magnitude
                int point = new int[]{100, 10, 1}[j]; // score magnitude

//                compare(id_curr, id_next, sortP_sel, point);
                compare(x, y, sortP_sel, point);

                // point, id_sel, id_sel point, sortPrio:
//                System.out.printf("%d, %s, %d, %s \n", point, x, x.getScore(), sortP_sel);
//                System.out.printf("%d, %s, %d, %s \n", point, y, y.getScore(), sortP_sel);
            }
            return x.getScore() - y.getScore();
        });

        return id_objs;
    }

    static int compare(ID a, ID b, String sortPrio, int pointWinning) {
        int point = pointWinning;
        switch (sortPrio) {
            case "Gender with Female first":
                if (a.isFemale()) {
                    a.minusScore(point);
                }
                if (b.isFemale()) {
                    b.minusScore(point);
                }
                break;
            case "Gender with Male first":
                if (a.isMale()) {
                    a.minusScore(point);
                }
                if (b.isMale()) {
                    b.minusScore(point);
                }
                break;
            case "Birth Day":
                if (a.getDay() < b.getDay()) {
                    b.addScore(point);
                } else if (a.getDay() > b.getDay()) {
                    a.addScore(point);
                }
                break;
            case "Birth Month":
                if (a.getMonth() < b.getMonth()) {
                    b.addScore(point);
                } else if (a.getMonth() > b.getMonth()) {
                    a.addScore(point);
                }
                break;
            case "Birth Year":
                if (a.getYear() < b.getMonth()) {
                    b.addScore(point);
                } else if (a.getMonth() > b.getMonth()) {
                    a.addScore(point);
                }
                break;
            case "Birthdate":
                if (a.getBirthdate_asInt() < b.getBirthdate_asInt()) {
                    b.addScore(point);
                } else if (a.getBirthdate_asInt() > b.getBirthdate_asInt()) {
                    a.addScore(point);
                }
                break;
            default:
        }
        return b.getScore() - a.getScore();
    }
    public static void main(String[] args) {

        run("980828-08-6312;010606-10-0473;980814-06-0431;980829-02-5810;050331-66-5893;980730-66-5010;050325-01-0215;550622-01-0481",
                "Gender with Male first",
                "Birth Day",
                "Birthdate");

    }
}
