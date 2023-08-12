package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class contract {
    String name;
    List<List<String>> global_variable_list = new ArrayList<>();
    List<List<List<String>>> function_list = new ArrayList<>();
    public contract(String name){
        this.name = name;
    }
    public void add_global_variable(List<String> variable){
        global_variable_list.add(variable);
    }
    public void add_function(List<List<String>> function){
        function_list.add(function);
    }
    public String get_contract_name(){
        return name;
    }
    public String get_global_variable_type(String variable_name) throws ValueNotFoundException {
        for(List<String> variable:global_variable_list){
            if(Objects.equals(variable.get(0), variable_name)){
                return variable.get(1);
            }
        }
        throw new ValueNotFoundException("variable "+variable_name+" not found");
    }
    public String get_global_variable_visibility(String variable_name) throws ValueNotFoundException {
        for(List<String> variable:global_variable_list){
            if(Objects.equals(variable.get(0), variable_name)){
                return variable.get(2);
            }
        }
        throw new ValueNotFoundException("variable "+variable_name+" not found");
    }
    public List<List<String>> get_function_local_variable(String function_name) throws ValueNotFoundException {
        for (List<List<String>> lists : function_list) {
            if (Objects.equals(function_name, lists.get(0).get(0))) {
                return lists;
            }
        }
        throw new ValueNotFoundException("function "+function_name+" not found");
    }

}
