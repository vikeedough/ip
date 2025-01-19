package controller;

import controller.Entry;

import java.util.ArrayList;

public class EntryList {
    private ArrayList<Entry> entries;

    public EntryList() {
        this.entries = new ArrayList<Entry>();
    }

    public String addEntry(Entry entry) {
        this.entries.add(entry);
        return "added: " + entry.getName();
    }

    public void printList() {
        for(int i=0; i < this.entries.size(); i++) {
            Entry curr = this.entries.get(i);
            System.out.println((i + 1) + "." + curr);
        }
    }

    public Entry get(int index) {
        return this.entries.get(index);
    }
}
