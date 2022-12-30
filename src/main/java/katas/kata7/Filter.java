package katas.kata7;

public class Filter {
        
        private final String filter;
        private int filterValue;
        
        public Filter(String filter) {
            this.filter = filter;
        }

        public Filter(String filter, int filterValue) {
            this.filter = filter;
            this.filterValue = filterValue;
        }

        public String getFilter() {
            return filter;
        }

        public int getFilterValue() {
            return filterValue;
        }
    }