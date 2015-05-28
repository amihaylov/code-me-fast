package com.boyko.codemefast;

import javax.swing.JComboBox;

public class ComboCalendar {
    static JComboBox<String> daysCombo() {
        JComboBox<String> days = new JComboBox<String>();
        days.addItem("1");
        days.addItem("2");
        days.addItem("3");
        days.addItem("4");
        days.addItem("5");
        days.addItem("6");
        days.addItem("7");
        days.addItem("8");
        days.addItem("9");
        days.addItem("10");
        days.addItem("11");
        days.addItem("12");
        days.addItem("13");
        days.addItem("14");
        days.addItem("15");
        days.addItem("16");
        days.addItem("17");
        days.addItem("18");
        days.addItem("19");
        days.addItem("20");
        days.addItem("21");
        days.addItem("22");
        days.addItem("23");
        days.addItem("24");
        days.addItem("25");
        days.addItem("26");
        days.addItem("27");
        days.addItem("28");
        days.addItem("29");
        days.addItem("30");
        days.addItem("31");
        return days;
    }

    static JComboBox<String> monthsCombo() {
        JComboBox<String> months = new JComboBox<String>();
        months.addItem("1");
        months.addItem("2");
        months.addItem("3");
        months.addItem("4");
        months.addItem("5");
        months.addItem("6");
        months.addItem("7");
        months.addItem("8");
        months.addItem("9");
        months.addItem("10");
        months.addItem("11");
        months.addItem("12");
        return months;
    }

    static JComboBox<String> yearsCombo() {
        JComboBox<String> years = new JComboBox<String>();
        years.addItem("2015");
        years.addItem("2016");
        years.addItem("2017");
        years.addItem("2018");
        years.addItem("2019");
        return years;
    }
}
