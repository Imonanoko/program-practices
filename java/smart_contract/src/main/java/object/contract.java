package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class contract {
    private static class function {
        //[name,type]
        List<List<String>> local_variable_List = new ArrayList<>();
        //[name,size,type]
        List<List<String>> local_array_List = new ArrayList<>();
        String name;
        String visibility;
        //每個row是[是否為陣列,參數名稱,型別]
        List<List<String>> parameter;

        public function(String name, String visibility, List<List<String>> parameter) {
            this.name = name;
            this.visibility = visibility;
            this.parameter = parameter;
        }
    }

    private static class struct {
        String name;
        //[name,type]
        List<List<String>> struct_variable_list = new ArrayList<>();
        //[name,size,type]
        List<List<String>> struct_array_List = new ArrayList<>();

        public struct(String name) {
            this.name = name;
        }
    }
    //繼承誰
    List<String> parents = new ArrayList<>();
    String name;
    //[name,type,visibility]
    List<List<String>> global_variable_list = new ArrayList<>();
    //[name,size,visibility,type]
    List<List<String>> array_list = new ArrayList<>();
    List<function> function_list = new ArrayList<>();
    List<struct> struct_list = new ArrayList<>();
    boolean init;
    public contract(String name) {
        this.name = name;
        init=false;
    }
    public void NeedInit(){
        init=true;
    }
    public void add_global_variable(List<String> variable) {
        global_variable_list.add(variable);
    }

    public void add_array(List<String> array) {
        array_list.add(array);
    }

    public void add_function(String name, String visibility, List<List<String>> parameter) {
        function_list.add(new function(name, visibility, parameter));
    }

    public void add_function_local_variable(List<List<String>> local_variable_List) {
        int index = function_list.size() - 1;
        function_list.get(index).local_variable_List = local_variable_List;
    }

    public void add_function_array_variable(List<List<String>> local_array_List) {
        int index = function_list.size() - 1;
        function_list.get(index).local_array_List = local_array_List;
    }

    public void add_struct(String name) {
        struct_list.add(new struct(name));
    }

    public void add_struct_variable(List<List<String>> struct_variable_List) {
        int index = struct_list.size()-1;
        struct_list.get(index).struct_variable_list = struct_variable_List;
    }

    public void add_struct_array_variable(List<List<String>> struct_array_List) {
        int index = struct_list.size()-1;
        struct_list.get(index).struct_array_List = struct_array_List;
    }
    public void add_inheritance(List<String> parents){
        this.parents = parents;

    }

    public void display() {
        System.out.println("contract name: " + name+" ,parents: "+parents+"init:"+(init?"yes":"no"));
        System.out.println("    Global variable:");
        for (List<String> Global_variable : global_variable_list) {
            if (!Global_variable.isEmpty())
                System.out.println("        variable name: " + Global_variable.get(0) + " ,type: " + Global_variable.get(1) + " ,visibility: " + Global_variable.get(2));
        }
        System.out.println("    Global array variable:");
        for (List<String> array : array_list) {
            if (!array.isEmpty())
                System.out.println("        array name: " + array.get(0) +" ,size: " + array.get(1) + " ,visibility: " + array.get(2)+ " ,type: "+ array.get(3));
        }
        System.out.println("function:");
        for(function f: function_list){
            System.out.println("function name: "+f.name+" ,visibility: "+f.visibility+" ,parameter: "+f.parameter);
            System.out.println("    local variable:");
            for(List<String> v:f.local_variable_List){
                System.out.println("        variable name: "+v.get(0)+" ,type: "+v.get(1));
            }
            System.out.println("    local array variable:");
            for(List<String> av:f.local_array_List){
                System.out.println("        variable name: "+av.get(0)+" ,size: "+av.get(1)+" ,type: "+av.get(2));
            }
        }
        System.out.println("struct:");
        for(struct s: struct_list){
            System.out.println("struct name: "+s.name);
            System.out.println("    struct variable name:");
            for(List<String> v:s.struct_variable_list){
                System.out.println("        variable name: "+v.get(0)+" ,type: "+v.get(1));
            }
            System.out.println("    struct array variable name: ");
            for(List<String> av:s.struct_array_List){
                System.out.println("        variable name: "+av.get(0)+" ,size: "+av.get(1)+" ,type: "+av.get(2));
            }
        }
    }

    public String get_contract_name() {
        return name;
    }

    public List<List<String>> get_global_variable_list() {
        return this.global_variable_list;
    }

    public String get_global_variable_type(String variable_name) throws ValueNotFoundException {
        for (List<String> variable : global_variable_list) {
            if (Objects.equals(variable.get(0), variable_name)) {
                return variable.get(1);
            }
        }
        throw new ValueNotFoundException("variable " + variable_name + " not found");
    }

    public String get_global_variable_visibility(String variable_name) throws ValueNotFoundException {
        for (List<String> variable : global_variable_list) {
            if (Objects.equals(variable.get(0), variable_name)) {
                return variable.get(2);
            }
        }
        throw new ValueNotFoundException("variable " + variable_name + " not found");
    }


}
