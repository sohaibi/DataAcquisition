import pandas

data = pandas.read_csv('results.csv')


#selects rows over a range of values specified by lower_bound and upper_bound
def get_range(lower_bound, upper_bound, column_name):
    return (data.loc[(data[column_name] >= lower_bound) & 
            (data[column_name] <= upper_bound)])


#selects row over a range of values specified by value
def get_values(value, column_name):
    return data.loc[(data[column_name] == value)]


#returns the average values from a column
def get_avg(column_name):
    return data[column_name].mean()


#returns all the values in the column
def get_var(column_name):
    return data[column_name]


#returns maximum value in the column
def get_max(column_name):
    return data[column_name].max()


#returns standard deviation of values in the column
def get_std(column_name):
    return data[column_name].std()


#returns description of the values in the column
def return_desc(column_name):
    return data[column_name].describe()


if __name__ == '__main__':
    get_var("t")


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