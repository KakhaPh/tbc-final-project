package ge.tbc.testautomation.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.RateValidationSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateValidationTest {

    private static RateValidationSteps rateValidationSteps;

    @BeforeAll
    static void setUp() {
        // Initialize steps once for all tests. This fetches the auth tokens.
        rateValidationSteps = new RateValidationSteps();
    }

    @Test
    @DisplayName("Validate that the single exchange buy rate matches the latest commercial buy rate")
    void buyRatesShouldMatch() {
        // 1. Get the 'buyRate' from the simple GET endpoint
        double singleBuyRate = rateValidationSteps.getSingleBuyRate();

        // 2. Get the 'buy' rate from the authenticated POST endpoint
        double commercialBuyRate = rateValidationSteps.getLatestCommercialBuyRate();

        // 3. Assert that the values are equal
        System.out.println("\n--- Performing Final Validation ---");
        System.out.printf("Asserting that single rate (%.4f) equals latest commercial rate (%.4f).%n", singleBuyRate, commercialBuyRate);

        // Use a delta for comparing doubles to handle floating-point inaccuracies
        double delta = 0.0001;
        assertEquals(singleBuyRate, commercialBuyRate, delta, "The single buy rate should match the latest commercial buy rate");

        System.out.println("\n✅ SUCCESS: The rates match!");
    }
}