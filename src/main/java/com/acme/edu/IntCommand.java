package com.acme.edu;

/**
 * Created by Java_5 on 17.07.2019.
 */
public class IntCommand extends Command {
    private int message;

    public String state = "Int";

    public IntCommand(int message) {
        this.message = message;
    }

    public Integer getMessage(){
        return this.message;
    }

    public String decorate() {
        return("primitive: ");
    }

    public String toString() {
        return("primitive: " + message);
    }

    public void accumulate(Command prevCommand) {
        this.message += (int)prevCommand.getMessage();
    }

    public boolean isOverflowed(Command prevCommand) {
        int accumulatedSumm = (int)prevCommand.getMessage();

        int diffPos = Integer.MAX_VALUE - accumulatedSumm;
        int diffNeg = Integer.MIN_VALUE - accumulatedSumm;

        boolean isPositiveOverflow = (accumulatedSumm >= 0) && (diffPos < message);
        boolean isNegativeOverflow = (accumulatedSumm < 0) && (diffNeg > message);

        return isPositiveOverflow || isNegativeOverflow;
    }
}
