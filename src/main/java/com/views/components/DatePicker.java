package com.views.components;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

public class DatePicker extends JPanel {
    private int hGap;
    private int minYear;
    private int maxYear;

    private TComboBox<Integer> dayOptions;
    private TComboBox<Integer> monthOptions;
    private TComboBox<Integer> yearOptions;

    public DatePicker() {
        super();

        hGap = 0;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(1, 3, this.hGap, 0));

        dayOptions = new TComboBox<Integer>();
        monthOptions = new TComboBox<Integer>();
        yearOptions = new TComboBox<Integer>();

        setMonthOptions();
        setYearOptions();
        setDayOptions();

        monthOptions.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setDayOptions();
                }
            }
        });

        add(dayOptions);
        add(monthOptions);
        add(yearOptions);
    }

    public void setHGap(int hGap) {
        this.hGap = hGap;
        ((GridLayout)this.getLayout()).setHgap(this.hGap);
    }

    public void setRangeYear(int min, int max) {
        this.minYear = min;
        this.maxYear = max;
    }

    public int getDay() {
        return (int) dayOptions.getSelectedItem();
    }

    public int getMonth() {
        return (int) monthOptions.getSelectedItem();
    }

    public int getYear() {
        return (int) yearOptions.getSelectedItem();
    }

    private List<Integer> getListDayOfMonth() {
        YearMonth yearMonthObj = YearMonth.of((int) monthOptions.getSelectedItem(),
                (int) monthOptions.getSelectedItem());
        int monthLen = yearMonthObj.lengthOfMonth();

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= monthLen; i++) {
            list.add(i);
        }

        return list;
    }

    private List<Integer> getListMonthOfYear() {
        return Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
    }

    private List<Integer> getListYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int currYear = cal.get(Calendar.YEAR);

        int min = minYear == 0 ? 1950 : minYear;
        int max = maxYear == 0 ? currYear : maxYear - 16;

        List<Integer> listYear = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            listYear.add(i);
        }

        return listYear;
    }

    private void setYearOptions() {
        yearOptions.removeAllItems();
        getListYear().forEach(year -> {
            yearOptions.addItem(year);
        });
    }

    private void setDayOptions() {
        dayOptions.removeAllItems();
        getListDayOfMonth().forEach(day -> {
            dayOptions.addItem(day);
        });
    }

    private void setMonthOptions() {
        monthOptions.removeAllItems();
        getListMonthOfYear().forEach(month -> {
            monthOptions.addItem(month);
        });
    }
}
