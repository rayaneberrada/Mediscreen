package fr.openclassrooms.rayane.diabetecalculator.service;

import fr.openclassrooms.rayane.diabetecalculator.constant.RiskLevelConstant;
import fr.openclassrooms.rayane.diabetecalculator.constant.SymptomsConstant;
import fr.openclassrooms.rayane.diabetecalculator.entity.Note;
import fr.openclassrooms.rayane.diabetecalculator.entity.Patient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RiskCalculatorServiceTest {

    static RisckCalculatorService risckCalculatorService = new RisckCalculatorService();

    static Patient patientMaleOverThirty;

    static Patient patientFemaleOverTHirty;

    static Patient patientMaleUnderThirty;

    static Patient patientFemaleUnderTHirty;

    static List<Note> notes = new ArrayList<>();

    @BeforeAll
    static void setUp() throws ParseException {
        patientMaleOverThirty = new Patient("test male over thirty", null, "H", "test", "000");
        patientFemaleOverTHirty = new Patient("test female over thirty", null, "F", "test", "000");
        patientMaleUnderThirty = new Patient("test male under thirty", null, "H", "test", "000");
        patientFemaleUnderTHirty = new Patient("test female under thirty", null, "F", "test", "000");
        patientMaleOverThirty.setDob("1960-12-31");
        patientFemaleOverTHirty.setDob("1960-12-31");
        patientMaleUnderThirty.setDob("1999-12-31");
        patientFemaleUnderTHirty.setDob("1999-12-31");


        Note noteOne = new Note();
        noteOne.setFamilyName("Test TestNone");
        noteOne.setNote("\"Le patient déclare qu'il ne se sent pas si fatigué que ça\n" +
                "Fumeur, il a arrêté dans les 12 mois précédents\n" +
                "Tests de laboratoire indiquant que les anticorps sont élevés\"");

        Note noteTwo = new Note();
        noteTwo.setFamilyName("Test TestNone");
        noteTwo.setNote("\"Le patient déclare qu'il se sent fatigué pendant la journée\n" +
                "Il se plaint également de douleurs musculaires\n" +
                "Tests de laboratoire indiquant une microalbumine élevée\"");

        Note noteThree = new Note();
        noteThree.setFamilyName("Test TestNone");
        noteThree.setNote("\"Le patient déclare qu'il ne se sent pas si fatigué que ça\n" +
                "Fumeur, il a arrêté dans les 12 mois précédents\n" +
                "Tests de laboratoire indiquant que les anticorps sont élevés\"");

        notes.add(noteOne);
        notes.add(noteTwo);
        notes.add(noteThree);
    }

    public void verifyCalculateNoteOccurenceParseAllSymptoms() throws ParseException {
        // GIVEN
        Note noteWithAllSymptoms = new Note();
        noteWithAllSymptoms.setFamilyName("Test TestNone");
        noteWithAllSymptoms.setNote("La taille et le poids et le cholestérol et le vertige  et les anticorps " +
                " et les Microalbumine, être fumeur ainsi qu'être anormal. Avoir une Hémoglobine A1C plus une mauvaise réaction et une Rechute");



        // WHEN
        int allSymptoms = risckCalculatorService.calculateNoteSymptomsOccurence(noteWithAllSymptoms);

        // THEN
        assertThat(allSymptoms).isEqualTo(SymptomsConstant.values().length);
    }

    /*********************************************************************************************************************
     *  Safe tests
     **********************************************************************************************************************/

    @Test
    public void checkPatientIsSafeSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithoutSymptoms = new Note();
        noteWithoutSymptoms.setFamilyName("Test TestNone");
        noteWithoutSymptoms.setNote("");

        notes.add(noteWithoutSymptoms);

        // WHEN
        RiskLevelConstant isSafeMaleOverThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isSafeFemaleOverThirty = risckCalculatorService.calculateRisk(notes, patientFemaleOverTHirty);
        RiskLevelConstant isSafeMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);
        RiskLevelConstant isSafeFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isSafeMaleOverThirty).isEqualTo(RiskLevelConstant.NONE);
        assertThat(isSafeFemaleOverThirty).isEqualTo(RiskLevelConstant.NONE);
        assertThat(isSafeMaleUnderThirty).isEqualTo(RiskLevelConstant.NONE);
        assertThat(isSafeFemaleUnderThirty).isEqualTo(RiskLevelConstant.NONE);
    }

    @Test
    public void checkPatientIsSafeFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithoutSymptoms = new Note();
        noteWithoutSymptoms.setFamilyName("Test TestNone");
        noteWithoutSymptoms.setNote("Il y a un symptome: fumeur");

        notes.add(noteWithoutSymptoms);

        // WHEN
        RiskLevelConstant isSafeMaleOverThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isSafeFemaleOverThirty = risckCalculatorService.calculateRisk(notes, patientFemaleOverTHirty);
        RiskLevelConstant isSafeMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);
        RiskLevelConstant isSafeFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isSafeMaleOverThirty).isEqualTo(null);
        assertThat(isSafeFemaleOverThirty).isEqualTo(null);
        assertThat(isSafeMaleUnderThirty).isEqualTo(null);
        assertThat(isSafeFemaleUnderThirty).isEqualTo(null);
    }

    /*********************************************************************************************************************
     *  Borderline tests
     **********************************************************************************************************************/

    @Test
    public void checkPatientIsBorderlineOverThirtySucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithTwoSymptoms = new Note();
        noteWithTwoSymptoms.setFamilyName("Test TestNone");
        noteWithTwoSymptoms.setNote("La taille et le poids font deux symptomes");

        notes.add(noteWithTwoSymptoms);

        // WHEN
        RiskLevelConstant isBorderlineMaleOverThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isBorderlineFemaleOverThirty = risckCalculatorService.calculateRisk(notes, patientFemaleOverTHirty);

        // THEN
        assertThat(isBorderlineMaleOverThirty).isEqualTo(RiskLevelConstant.BORDERLINE);
        assertThat(isBorderlineFemaleOverThirty).isEqualTo(RiskLevelConstant.BORDERLINE);
    }

    @Test
    public void checkPatientIsBorderlineUnderThirtyFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithTwoSymptoms = new Note();
        noteWithTwoSymptoms.setFamilyName("Test TestNone");
        noteWithTwoSymptoms.setNote("La taille et le poids font deux symptomes");

        notes.add(noteWithTwoSymptoms);

        // WHEN
        RiskLevelConstant isBorderlineMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);
        RiskLevelConstant isBorderlineFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isBorderlineMaleUnderThirty).isEqualTo(null);
        assertThat(isBorderlineFemaleUnderThirty).isEqualTo(null);
    }

    /*********************************************************************************************************************
     *  Danger tests
     **********************************************************************************************************************/

    @Test
    public void checkMalePatientUnderThirtyIsInDangerSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithThreeSymptoms = new Note();
        noteWithThreeSymptoms.setFamilyName("Test TestNone");
        noteWithThreeSymptoms.setNote("La taille, le poids et le cholestérol font trois");

        notes.add(noteWithThreeSymptoms);

        // WHEN
        RiskLevelConstant isInDangerMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);

        // THEN
        assertThat(isInDangerMaleUnderThirty).isEqualTo(RiskLevelConstant.DANGER);
    }

    @Test
    public void checkMalePatientUnderThirtyIsInDangerFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithTwoSymptoms = new Note();
        noteWithTwoSymptoms.setFamilyName("Test TestNone");
        noteWithTwoSymptoms.setNote("La taille et le poids  font deux");

        notes.add(noteWithTwoSymptoms);

        // WHEN
        RiskLevelConstant isInDangerMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);

        // THEN
        assertThat(isInDangerMaleUnderThirty).isEqualTo(null);
    }

    @Test
    public void checkFemalePatientUnderThirtyIsInDangerSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithFourSymptoms = new Note();
        noteWithFourSymptoms.setFamilyName("Test TestNone");
        noteWithFourSymptoms.setNote("La taille et le poids et le cholestérol ainsi qu'être anormal font quatre");

        notes.add(noteWithFourSymptoms);

        // WHEN
        RiskLevelConstant isInDangerFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isInDangerFemaleUnderThirty).isEqualTo(RiskLevelConstant.DANGER);
    }

    @Test
    public void checkFemalePatientOverThirtyIsInDangerSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithSixSymptoms = new Note();
        noteWithSixSymptoms.setFamilyName("Test TestNone");
        noteWithSixSymptoms.setNote("La taille et le poids et le cholestérol et le vertige  et les anticorps ainsi qu'être anormal font six");

        notes.add(noteWithSixSymptoms);

        // WHEN
        RiskLevelConstant isInDangerMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isInDangerFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isInDangerMaleUnderThirty).isEqualTo(RiskLevelConstant.DANGER);
        assertThat(isInDangerFemaleUnderThirty).isEqualTo(RiskLevelConstant.DANGER);
    }

    @Test
    public void checkFemaleAndMalePatientOverThirtyIsInDangerFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithFiveSymptoms = new Note();
        noteWithFiveSymptoms.setFamilyName("Test TestNone");
        noteWithFiveSymptoms.setNote("La taille et le poids et le cholestérol et le vertige ainsi qu'être anormal font cinq");

        notes.add(noteWithFiveSymptoms);

        // WHEN
        RiskLevelConstant isInDangerlineMaleOverThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isInDangerlineFemaleOverThirty = risckCalculatorService.calculateRisk(notes, patientFemaleOverTHirty);

        // THEN
        assertThat(isInDangerlineMaleOverThirty).isEqualTo(RiskLevelConstant.BORDERLINE);
        assertThat(isInDangerlineFemaleOverThirty).isEqualTo(RiskLevelConstant.BORDERLINE);
    }

    /*********************************************************************************************************************
     *  EarlyOnSet tests
     **********************************************************************************************************************/

    @Test
    public void checkMalePatientUnderThirtyIsEarlyOnSetSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithFiveSymptoms = new Note();
        noteWithFiveSymptoms.setFamilyName("Test TestNone");
        noteWithFiveSymptoms.setNote("La taille et le poids et le cholestérol et le vertige ainsi qu'être anormal font cinq");

        notes.add(noteWithFiveSymptoms);


        // WHEN
        RiskLevelConstant isEarlyOnSetMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);

        // THEN
        assertThat(isEarlyOnSetMaleUnderThirty).isEqualTo(RiskLevelConstant.EARLYONSET);
    }

    @Test
    public void checkMalePatientUnderThirtyIsEarlyOnSetFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithFourSymptoms = new Note();
        noteWithFourSymptoms.setFamilyName("Test TestNone");
        noteWithFourSymptoms.setNote("La taille et le poids et le cholestérol ainsi qu'être anormal font quatre");

        notes.add(noteWithFourSymptoms);

        // WHEN
        RiskLevelConstant isEarlyOnSetMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleUnderThirty);

        // THEN
        assertThat(isEarlyOnSetMaleUnderThirty).isEqualTo(RiskLevelConstant.DANGER);
    }

    @Test
    public void checkFemalePatientUnderThirtyIsEarlyOnSetSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithSevenSymptoms = new Note();
        noteWithSevenSymptoms.setFamilyName("Test TestNone");
        noteWithSevenSymptoms.setNote("La taille et le poids et le cholestérol et le vertige  et les anticorps " +
                " et les Microalbumine ainsi qu'être anormal font sept");

        notes.add(noteWithSevenSymptoms);

        // WHEN
        RiskLevelConstant isEarlyOnSetFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isEarlyOnSetFemaleUnderThirty).isEqualTo(RiskLevelConstant.EARLYONSET);
    }

    @Test
    public void checkFemalePatientUnderThirtyIsEarlyOnSetFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithSixSymptoms = new Note();
        noteWithSixSymptoms.setFamilyName("Test TestNone");
        noteWithSixSymptoms.setNote("La taille et le poids et le cholestérol et le vertige  et les anticorps ainsi qu'être anormal font six");

        notes.add(noteWithSixSymptoms);

        // WHEN
        RiskLevelConstant isEarlyOnSetFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isEarlyOnSetFemaleUnderThirty).isEqualTo(RiskLevelConstant.DANGER);
    }

    @Test
    public void checkFemalePatientOverThirtyIsEarlyOnSetSucceed() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithEightSymptoms = new Note();
        noteWithEightSymptoms.setFamilyName("Test TestNone");
        noteWithEightSymptoms.setNote("La taille et le poids et le cholestérol et le vertige  et les anticorps " +
                " et les Microalbumine, être fumeur ainsi qu'être anormal font huit");

        notes.add(noteWithEightSymptoms);

        // WHEN
        RiskLevelConstant isEarlyOnSetMaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isEarlyOnSetFemaleUnderThirty = risckCalculatorService.calculateRisk(notes, patientFemaleUnderTHirty);

        // THEN
        assertThat(isEarlyOnSetMaleUnderThirty).isEqualTo(RiskLevelConstant.EARLYONSET);
        assertThat(isEarlyOnSetFemaleUnderThirty).isEqualTo(RiskLevelConstant.EARLYONSET);
    }

    @Test
    public void checkFemaleAndMalePatientOverThirtyIsEarlyOnSetFail() {
        // GIVEN
        List<Note> notes = new ArrayList<>();

        Note noteWithSevenSymptoms = new Note();
        noteWithSevenSymptoms.setFamilyName("Test TestNone");
        noteWithSevenSymptoms.setNote("La taille et le poids et le cholestérol et le vertige  et les anticorps " +
                " et les Microalbumine ainsi qu'être anormal font sept");

        notes.add(noteWithSevenSymptoms);

        // WHEN
        RiskLevelConstant isEarlyOnSetlineMaleOverThirty = risckCalculatorService.calculateRisk(notes, patientMaleOverThirty);
        RiskLevelConstant isEarlyOnSetlineFemaleOverThirty = risckCalculatorService.calculateRisk(notes, patientFemaleOverTHirty);

        // THEN
        assertThat(isEarlyOnSetlineMaleOverThirty).isEqualTo(RiskLevelConstant.DANGER);
        assertThat(isEarlyOnSetlineFemaleOverThirty).isEqualTo(RiskLevelConstant.DANGER);
    }
}
