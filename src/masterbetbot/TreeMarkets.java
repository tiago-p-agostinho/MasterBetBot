package masterbetbot;

import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

public class TreeMarkets {
    TreeView<String> treeView = new TreeView<>();
    private MasterBetBot master;
    
    List<Employee> employees = Arrays.<Employee>asList(
            new Employee("Ethan Williams", "Sales Department"),
            new Employee("Emmasadhjkasjhdjksahdsahdes", "Sfsdfsdfsdfsdfdsfsdtment"),
            new Employee("Michael Brown", "Sales Department"),
            new Employee("Anna Black", "Sales Department"),
            new Employee("Rodger York", "Sales Department"),
            new Employee("Susdsfsdfsdfsdfdsfdsfsdfdsfsdfdsfsdfsdfllins", "Salesdfdsfdsfdsfdsfdsfdstment"),
            new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"),
            new Employee("Gregofdsfsdfdsfdsfsdfdsfdsfsdfsdfsdfdsfsdfdsfdsfdsfdsfdsfsdfsdith", "IT Support"),
            new Employee("Jacob Smith", "Accounts Department"),
            new Employee("Isabesdason", "Accounts Department"),
            new Employee("Esadsaliams", "Sales Department"),
            new Employee("Emasdases", "Sales Department"),
            new Employee("MiasdBrown", "Sales Department"),
            new Employee("Anasdasdack", "Sales Department"),
            new Employee("Rodsadork", "Sales Department"),
            new Employee("dsad", "Sales Department"),
            new Employee("Mikcxcxzham", "IT Support"),
            new Employee("Judcxcyer", "IT Support"),
            new Employee("Gregorkdfjaskldjaskjdkasjkldcmith", "IT Support"),
            new Employee("Gregsdasth", "IT Support"),
            new Employee("Greasdmith", "IT Support"),
            new Employee("Gregsdascmith", "IT Support"),
            new Employee("Gresaddazcmith", "IT Support"),
            new Employee("Gregorxzcmith", "IT Support"),
            new Employee("Jacoxczmith", "Accounts Department"),
            new Employee("Isabellzhnson", "Accounts Department"));
   
    TreeItem<String> rootNode = 
        new TreeItem<>("MyCompany Human Resources", null);
  
    public TreeMarkets(MasterBetBot mbb){
        this.master=mbb;
    }
    
    public void start() {
      for (Employee employee : employees) {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            empLeaf.setExpanded(true);
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(employee.getDepartment())){
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<>(
                    employee.getDepartment()
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

      treeView = new TreeView<>(rootNode);
      treeView.setOnMouseClicked((MouseEvent mouseEvent) -> {
          if(mouseEvent.getClickCount() == 2){
              TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().
                      getSelectedItem();
              System.out.println("Selected Text : " + item.getValue());
              
              // Create New Tab
              Tab tabData = new Tab();
              TabPane tabPane = master.getTabPane();
              Label tabALabel = new Label("Test");
              tabData.setGraphic(tabALabel);
              
             tabPane.getTabs().add(tabData);
          }
      });
    }
 
    public TreeView getTreeView(){
      return treeView;
    }
    
    public static class Employee {
 
        private final SimpleStringProperty name;
        private final SimpleStringProperty department;
 
        private Employee(String name, String department) {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }
 
        public String getName() {
            return name.get();
        }
 
        public void setName(String fName) {
            name.set(fName);
        }
 
        public String getDepartment() {
            return department.get();
        }
 
        public void setDepartment(String fName) {
            department.set(fName);
        }
    }
}