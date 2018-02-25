package com.inql.aisd.redblack;

public enum Color {
    RED{
        @Override
        public String toString(){
            return "\u001B[31m";
        }
    },
    BLACK{
        @Override
        public String toString(){
            return "\u001B[30m";
        }
    }
}
