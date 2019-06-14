public class Main {
//    public static void main(String[] args) {
//        Transaction session = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//        System.out.println(HibernateUtil.getSessionFactory().toString());
//        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
//
//        Subscription subscription = Subscription.builder().startDate(new Date(3004)).endDate(new Date(5004)).price(2400)
//                .workoutStartTime(new Time(3000)).workoutEndTime(new Time(80000)).build();
//
//        System.out.println(subscription);
//
//        subscriptionService.createSubscription(subscription);
//        System.out.println(subscriptionService.getAllSubscriptions());
//        session.commit();
//    }
}