import pandas
import pandas as pd
import csv

data = pandas.read_csv('results.csv')
titles =['t','lap','pos_x','pos_y','x','v','a_long','a_lat','cell_temp','cell_soc','rotor_temp_f','rotor_heat_f','rotor_temp_r','rotor_heat_r','FIN','FON','RIN','RON','yaw','pitch','roll','field_weakening','frdf','fldf','rrdf','rldf','long_cf_f_inside','long_cf_f_outside','long_cf_r_inside','long_cf_r_outside','lat_cf_f_inside','lat_cf_f_outside','lat_cf_r_inside','lat_cf_r_outside','max_lat_force_f','max_long_force_f','max_lat_force_r','max_long_force_r','front_lat_force','front_long_force','rear_lat_force','rear_long_force','max_motor_power','motor_speed_limited','traction_limited','motor_eff','battery_Re','battery_heat_removed','cell_heat_w','battery_heat_w','battery_current','cell_current','battery_vout','power_battery','a_long_g','a_lat_g','dW_lon','dW_lat_f','dW_lat_r','dW_spr_f','dW_geo_f','dW_uns_f','dW_spr_r','dW_geo_r','dW_uns_r','dW_spr_x','dW_geo_x','dW_uns_rx','dW_uns_fx','weight','motor_torque','motor_rpm','power_driving','power_motor','power_inverter','motor_heat_w','inv_heat_w','downforce','dragforce','sideforce','v_mph']
#selects rows over a range of values specified by lower_bound and upper_bound
#stored in a dictonary where the key is the name of the data ex: battery_current, sideforce
def get_range(lower_bound, upper_bound, column_name):
    my_dict={}
    selected = data.loc[(data[column_name] >= lower_bound) &
                     (data[column_name] <= upper_bound)]
    print(selected)
    iindex = 0;
    titleindex = 0;
    for iindex in range(81):
        selected = data.loc[(data[column_name] >= lower_bound) &
                            (data[column_name] <= upper_bound)]
        selected = selected.iloc[:, iindex].tolist()
        current_title = titles[titleindex]
        iindex = iindex + 1
        titleindex = titleindex + 1
        my_dict[current_title] = selected
    print(my_dict)
    return(my_dict)


#selects row over a range of values specified by value and returns a dictionary with the value stored where the key is the name of the data
#ex: battery_current, sideforce
def get_values(value, column_name):
    iindex = 0;
    titleindex = 0;
    my_dict={}
    for iindex in range(81):
        selected = data.loc[(data[column_name] == value)]
        selected = selected.iloc[:, iindex].tolist()
        current_title = titles[titleindex]
        iindex = iindex + 1
        titleindex = titleindex + 1
        my_dict[current_title] = selected
    print(my_dict)
    return(my_dict)


#returns the average values from a column
def get_avg(column_name):
    return data[column_name].mean()


#returns all the values in the column in a list
def get_var(column_name):
    table = []
    with open('results.csv', mode='r') as csv_file:
        csv_reader = csv.DictReader(csv_file)
        for row in csv_reader:
            table.append(row[column_name])
        print(table)
        #print(data[column_name])
    return table


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