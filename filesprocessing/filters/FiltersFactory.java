package filesprocessing.filters;

import filesprocessing.exceptions.ErrorTypeOneException;
import filesprocessing.filters.booleanCompareValueFilters.ExecutableFilter;
import filesprocessing.filters.booleanCompareValueFilters.HiddenFilter;
import filesprocessing.filters.booleanCompareValueFilters.WritableFilter;
import filesprocessing.filters.doubleCompareValueFilters.BetweenFilter;
import filesprocessing.filters.doubleCompareValueFilters.GreaterThanFilter;
import filesprocessing.filters.doubleCompareValueFilters.SmallerThanFilter;
import filesprocessing.filters.stringCompareValueFilters.ContainsFilter;
import filesprocessing.filters.stringCompareValueFilters.FileFilter;
import filesprocessing.filters.stringCompareValueFilters.PrefixFilter;
import filesprocessing.filters.stringCompareValueFilters.SuffixFilter;

public class FiltersFactory {

    public static SuperFilter createSuitedFilter(String [] filterCommandLineStrings,
                                                 int filterCommandLineNumber) throws ErrorTypeOneException {
        if (filterCommandLineStrings.length > 4) {
            throw new ErrorTypeOneException(filterCommandLineNumber);
        }
        if (filterCommandLineStrings.length >= 1) {
            SuperFilter filter = new AllFilter();
            String filterName = filterCommandLineStrings[0];
            String lastParameter = filterCommandLineStrings[filterCommandLineStrings.length - 1];
            if (filterName.equals("all")) {
                if (lastParameter.equals("NOT"))
                    filter = new NotFilterDecorator(filter);
                return filter;
            }
            if (filterCommandLineStrings.length >= 2) {
                String firstParameter = filterCommandLineStrings[1];
                if(!filterName.equals("between")){
                    if(filterCommandLineStrings.length > 3)
                        throw new ErrorTypeOneException(filterCommandLineNumber);
                    else if(filterCommandLineStrings.length == 3 && !lastParameter.equals("NOT"))
                        throw new ErrorTypeOneException(filterCommandLineNumber);
                }
                else{
                    if (filterCommandLineStrings.length < 3)
                        throw new ErrorTypeOneException(filterCommandLineNumber);
                    else if(filterCommandLineStrings.length == 4 && !lastParameter.equals("NOT"))
                        throw new ErrorTypeOneException(filterCommandLineNumber);
                }
                switch (filterName) {
                    case "executable": {
                        if (firstParameter.equals("YES") || firstParameter.equals("NO")) {
                            if (firstParameter.equals("YES"))
                                filter = new ExecutableFilter(true);
                            else
                                filter = new ExecutableFilter(false);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                        } else
                            throw new ErrorTypeOneException(filterCommandLineNumber);
                    }
                    case "hidden": {
                        if (firstParameter.equals("YES") || firstParameter.equals("NO")) {
                            if (firstParameter.equals("YES"))
                                filter = new HiddenFilter(true);
                            else
                                filter = new HiddenFilter(false);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                        } else
                            throw new ErrorTypeOneException(filterCommandLineNumber);
                    }
                    case "writable": {
                        if (firstParameter.equals("YES") || firstParameter.equals("NO")) {
                            if (firstParameter.equals("YES"))
                                filter = new WritableFilter(true);
                            else
                                filter = new WritableFilter(false);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                        } else
                            throw new ErrorTypeOneException(filterCommandLineNumber);
                    }
                    case "contains": {
                            filter = new ContainsFilter(firstParameter);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                    }
                    case "file": {
                        filter = new FileFilter(firstParameter);
                        if (lastParameter.equals("NOT"))
                            filter = new NotFilterDecorator(filter);
                        return filter;
                    }
                    case "prefix": {
                        filter = new PrefixFilter(firstParameter);
                        if (lastParameter.equals("NOT"))
                            filter = new NotFilterDecorator(filter);
                        return filter;
                    }
                    case "suffix": {
                        filter = new SuffixFilter(firstParameter);
                        if (lastParameter.equals("NOT"))
                            filter = new NotFilterDecorator(filter);
                        return filter;
                    }
                    case "between": {
                        String secondParameter = filterCommandLineStrings[2];
                        double doubleFirstParameter = Double.parseDouble(firstParameter);
                        double doubleSecondParameter = Double.parseDouble(secondParameter);
                        if ((doubleFirstParameter >= 0 && doubleSecondParameter >= 0) &&
                                (doubleFirstParameter <= doubleSecondParameter)) {
                            filter = new BetweenFilter(doubleFirstParameter, doubleSecondParameter);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                        } else {
                            throw new ErrorTypeOneException(filterCommandLineNumber);
                        }
                    }
                    case "greater_than": {
                        double doubleFirstParameter = Double.parseDouble(firstParameter);
                        if (doubleFirstParameter >= 0) {
                            filter = new GreaterThanFilter(doubleFirstParameter);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                        } else
                            throw new ErrorTypeOneException(filterCommandLineNumber);
                    }
                    case "smaller_than": {
                        double doubleFirstParameter = Double.parseDouble(firstParameter);
                        if (doubleFirstParameter >= 0) {
                            filter = new SmallerThanFilter(doubleFirstParameter);
                            if (lastParameter.equals("NOT"))
                                filter = new NotFilterDecorator(filter);
                            return filter;
                        } else
                            throw new ErrorTypeOneException(filterCommandLineNumber);
                    }
                }
                throw new ErrorTypeOneException(filterCommandLineNumber);
            }
        }
        throw new ErrorTypeOneException(filterCommandLineNumber);
    }
}
