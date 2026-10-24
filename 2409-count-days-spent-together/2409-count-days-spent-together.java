class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int aArrival = calculateDayOfYear(arriveAlice, daysInMonths);
        int aLeave = calculateDayOfYear(leaveAlice, daysInMonths);
        int bArrival = calculateDayOfYear(arriveBob, daysInMonths);
        int bLeave = calculateDayOfYear(leaveBob, daysInMonths);
        
        int startOverlap = Math.max(aArrival, bArrival);
        int endOverlap = Math.min(aLeave, bLeave);
        
        return Math.max(0, endOverlap - startOverlap + 1);
    }
    
    private int calculateDayOfYear(String date, int[] daysInMonths) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }
}