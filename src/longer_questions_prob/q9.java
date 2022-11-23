package longer_questions_prob;

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;


// Question PDF:
// https://drive.google.com/file/d/1t7IlMX453AmqKThWjza5-GIUNHI8dkml/view?usp=sharing
public class q9 {

    static Chef[] chefs = new Chef[]{new Chef(), new Chef(), new Chef()};
    static Customer[] customers;
    static class Chef {
        Customer currCustomer = null;
        String food = null;
        int quantity;
        int waitUntilReusable = 0;
        void prepFood(String food, int quantity, Customer customer) {
            // if the chef currently has no foods left to prepare
            if (this.food == null) {
                this.quantity = quantity;
                this.food = food;
                currCustomer = customer;
                waitUntilReusable += 5 * quantity;
            }
        }
        void sentFood() { currCustomer.getFood(food);}
        void update() {
            if (waitUntilReusable != 0) {
                waitUntilReusable -= 5;
                quantity--;
                sentFood();
            }
            // if no foods left to prepare, reset the String food to null
            if (quantity == 0) food = null;
        }
        boolean isReusable() {return waitUntilReusable == 0;}
    }
    static class Customer {
        String name;
        int waitTime = 0;
        boolean done = false;
        // self-written Comparator in Lambda form
        PriorityQueue<Object[]> foodToOrder = new PriorityQueue<>( (x,y) -> (int)y[1] - (int)x[1] ); // vary if order succeeded
        HashMap<String, Integer> foodsToWait; // unchanged

        Customer(String name, String order_info) {
            this.name = name;
            encodeOrder(order_info);
        }
        private void encodeOrder(String order_info) {
            String[] infos = order_info.split("%");
            foodsToWait = new HashMap<>();
            for (String info : infos) {
                String food_name = info.split(":")[0];
                int food_quantity = Integer.parseInt(info.split(":")[1]);
                Object[] curr = new Object[]{food_name, food_quantity};
                foodToOrder.add(curr);
                foodsToWait.put(food_name, food_quantity);
            }
        }
        void getFood(String food) {
            if (foodsToWait.containsKey(food) && foodsToWait.get(food) > 0) {
                foodsToWait.put(food, foodsToWait.get(food) - 1); // overwrite the food quantity value (lessen it)
            }
        }

        void update() {
            checkDone();
            while (foodToOrder.size() != 0) {
                if (findChefToPrep((String) foodToOrder.peek()[0], (Integer) foodToOrder.peek()[1], this)) {
                    foodToOrder.poll();
                }
                else {break;}
            }
            if (!done) waitTime += 5;
        }
        void checkDone() {
            int done_orders = 0;
            for (Map.Entry<String, Integer> food : foodsToWait.entrySet()) {
                if (food.getValue() == 0)
                    done_orders++;
            }
            if (done_orders == foodsToWait.size())
                done = true;
        }
    }
    static boolean findChefToPrep(String food, int quantity, Customer customer) {
        boolean found = false;
        for (Chef chef : chefs) {
            if (chef.isReusable()) { // find chef that is completely free
                chef.prepFood(food, quantity, customer);
                found = true;
                break;
            }
        }
        return found;
    }
    static void run(String start_info) {
        String[] infos = start_info.replaceAll("\s+", "").split(";");
        customers = new Customer[infos.length];
        // create customers and their orders
        for (int i = 0; i < infos.length; i++) {
            String name = infos[i].split("#")[0];
            String order_info = infos[i].split("#")[1];
            customers[i] = new Customer(name, order_info);
        }
        // every 5 min, update the status of customer and chef
        for (int i = 0; i < 50; i++) {
            for (Customer cu : customers)
                cu.update();
            for (Chef ch : chefs)
                ch.update();
        }
        // print the result of customer (how long to wait for food)
        for (Customer cu : customers) {
            int hrStart = 10;
            int hrToPlus = cu.waitTime / 60;
            int minToPlus = cu.waitTime % 60;
            System.out.printf("%s can collect food at %02d:%02d \n",cu.name,hrStart+hrToPlus, minToPlus);
        }
    }
    public static void main(String[] args) {
        String[] test_cases = {
                "Ali#burger:1%pizza:2% mee:3",
                "John#burger:2%pizza:1%mee:1;Mary#burger:1;Sam#pizza:3%burger:5",
                "Christine#burger:12%pizza:2%mee:3;Phantom#burger:1%mee:6;Raoul#pizza:1%burger:1",
                "Aminah#pizza:20;Seng#pizza:20;Summi#pizza:20;Ben#mee:20;Alex#pizza:10%mee:10",
                "Muthu#burger:3%pizza:2%mee:1;Barani#burger:5%mee:2;Sammy#pizza:6;Firdaus#mee:2%pizza:3#;Chong#burger:1%pizza:1",
                "John#burger:2%pizza:1%mee:1;Mary#burger:1;Sam#pizza:3%burger:5;Nick#mee:9%pizza:2#;Lee#burger:1",
                "Christine#burger:2%pizza:12%mee:3;Phantom#burger:1%mee:6;Raoul#pizza:1%burger:1;Sarah#pizza:5%mee:1;Andrew#burger:1%pizza:1",
                "Aminah#pizza:1;Seng#burger:1%pizza:3%mee:5;Summi#burger:1%pizza:3%mee:4"
        };
        for (String tc : test_cases) {
            run(tc);
            System.out.println("----------------------------------------");
        }
    }
}
