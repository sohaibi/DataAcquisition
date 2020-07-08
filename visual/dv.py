import pandas



#selects rows over a range of values specified by lower_bound and upper_bound
def range_of_values(lower_bound, upper_bound, column_name):
    data = pandas.read_csv('results.csv')
    datatest = data.loc[(data[column_name] >= lower_bound) & (data[column_name] <= upper_bound)]
    return datatest
#selects row over a range of values specified by value
def matching_values(value, column_name):
    data = pandas.read_csv('results.csv')
    datatest = data.loc[(data[column_name] == value)]
    print(datatest)
    return datatest
#returns the average values from a column
def return_avg(column_name):
    data = pandas.read_csv('results.csv')
    datatest = data[column_name].mean()
    print(datatest)
    return datatest
#returns all the values in the column
def search_by_column(column_name):
    data = pandas.read_csv('results.csv')
    datatest = data[column_name]
    print datatest
    return datatest
#returns maximum value in the column
def return_max(column_name):
    data = pandas.read_csv('results.csv')
    datatest = data[column_name].max()
    print(datatest)
    return datatest
#returns standard deviation of values in the column
def return_std(column_name):
    data = pandas.read_csv('results.csv')
    datatest = data[column_name].std()
    print(datatest)
    return datatest
#returns description of the values in the column
def return_desc(column_name):
    data = pandas.read_csv('results.csv')
    datatest = data[column_name].describe()
    print(datatest)
    return datatest


if __name__ == '__main__':
    search_by_column("t")
'''
def data_parse(lower_bound, int upper_bound, column_name, int )
data = pandas.read_csv('results.csv')
datatype = raw_input("Return by column, rownumber, or value?: ") #on newer python version the command may be just input()
if (datatype == "value" or datatype == "values" or  datatype == "return by values" or q2 == "Values" or q2 == "Values"): #
    q2 = raw_input("Range of values or singular values?: ")
    if (q2 == "range" or q2 == "range of values" or  q2 == "ranges" or q2 == "Range" or q2 == "Ranges"):
        column_name = raw_input("Input Column Name: ") #raw_input() versus input() python version compatibility
        lower_bound = input("Lower Bound: ")
        upper_bound = input("Upper Bound: ")
        datatest = data.loc[(data[column_name] >= lower_bound) & (data[column_name] <= upper_bound)]
        print(datatest)
    if (q2 == "value" or q2 == "values" or q2 == "singular values" or q2 == "Values" or q2 == "Values"):
        val = input("Input Data Value: ")
        datatest = data.loc[(data[column_name] == val)]
        print(datatest)
if (datatype == "column" or datatype == "columns" or datatype == "Column" or datatype == "Columns"):
    q3 = raw_input("Sort By?(")

'''


#print(datatest)