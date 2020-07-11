import plotly.graph_objects as go
import car_data


#Graph data
data_sets = {
'lap' : False,
'pos_x' : False,
'pos_y' : False,
'x' : False,
'v' : False,
'a_long' : False,
'a_lat' : False,
'cell_temp' : False,
'cell_soc' : False,
'rotor_temp_f' : False,
'rotor_heat_f' : False,
'rotor_temp_r' : False,
'rotor_heat_r' : False,
'FIN' : False,
'FON' : False,
'RIN' : False,
'RON' : False,
'yaw' : False,
'pitch' : False,
'roll' : False,
'field_weakening' : False,	
'frdf' : False,
'fldf' : False,
'rrdf' : False,
'rldf' : False,
'long_cf_f_inside' : False,	
'long_cf_f_outside' : False,	
'long_cf_r_inside' : False,
'long_cf_r_outside' : False,	
'lat_cf_f_inside' : False,
'lat_cf_f_outside' : False,	
'lat_cf_r_inside' : False,
'lat_cf_r_outside' : False,	
'max_lat_force_f' : False,
'max_long_force_f' : False,	
'max_lat_force_r' : False,
'max_long_force_r' : False,	
'front_lat_force' : False,
'front_long_force' : False,	
'rear_lat_force' : False,
'rear_long_force' : False,	
'max_motor_power' : False,	
'motor_speed_limited' : False,	
'traction_limited' : False,
'motor_eff' : False,
'battery_Re' : False,
'battery_heat_removed' : False,	
'cell_heat_w'	 : False,
'battery_heat_w'	 : False,
'battery_current' : False,	
'cell_current' : False,	
'battery_vout' : False,	
'power_battery' : False,
'a_long_g' : False,
'a_lat_g' : False,
'dW_lon' : False,
'dW_lat_f' : False,
'dW_lat_r' : False,
'dW_spr_f' : False,
'dW_geo_f' : False,
'dW_uns_f' : False,
'dW_spr_r' : False,
'dW_geo_r' : False,
'dW_uns_r' : False,
'dW_spr_x' : False,	
'dW_geo_x' : False,	
'dW_uns_rx' : False,	
'dW_uns_fx' : False,
'weight' : False,
'motor_torque' : False,	
'motor_rpm' : False,
'power_driving' : False,
'power_motor' : False,
'power_inverter' : False,
'motor_heat_w' : False,
'inv_heat_w' : False,
'downforce' : False,
'dragforce' : False,
'sideforce' : False,
'v_mph' : False,
}

#Displays every graph
def hideGraphs():
    for itm in data_sets:
        data_sets[itm] = False


#Data points every x seconds
listDelayValue = 0.5
class Graph:
    def __init__(self, x, data):
        self.x_axis = x
        self.y_axis = data
        for item in self.y_axis:
            data_sets[item] = True

    def display(self):
        figure = go.Figure()
        for key in data_sets:
            if data_sets[key]:
                figure.add_trace(go.Scatter(x=get_list(self.x_axis), y=get_list(key), mode='lines+markers', name=key))

        figure.show()
        
def main():
    Graph1 = Graph('t', ['pos_x','pos_y'])
    Graph1.display()
    print(data_sets['pos_x'])
    hideGraphs()
    print(data_sets['pos_x'])
    Graph2 = Graph('t', ['v','pos_y', 'a_long'])
    Graph2.display()

#Function to transfer data into array
def get_list(column_name):
    ax = (car_data.get_var(column_name))
    data = []
    listDelay = listDelayValue * 100
    for itm in ax:
        if listDelay == (listDelayValue * 100):
            data.append(itm)
            listDelay = 0
        listDelay += 1
    return data
if __name__ == '__main__':
    main()
'''
# Add traces
def display():
    figure = go.Figure()
    for key in data_sets:
        if data_sets[key]:
            figure.add_trace(go.Scatter(x=get_list("t"), y=get_list(key), mode='lines+markers', name=key))

    figure.show()
'''
