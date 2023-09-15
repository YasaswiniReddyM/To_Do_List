import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI extends JFrame implements ActionListener {

    //taskPanel - container for taskCComponentPanel
    //taskComponent-panel will store all of the task-components
    private JPanel taskPanel,taskComponentPanel;
    public ToDoListGUI(){
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(Constraints.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGUIComponents();
    }

    private void addGUIComponents(){
        //banner text
        JLabel bannerLabel=new JLabel("TO DO LIST");
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 35);
        bannerLabel.setFont(titleFont);
        bannerLabel.setBounds(
                (Constraints.GUI_SIZE.width-bannerLabel.getPreferredSize().width)/2,
                15,
                Constraints.BANNER_SIZE.width,
                Constraints.BANNER_SIZE.height
        );

        //taskPanel
        taskPanel=new JPanel();

        //taskComponentPanel
        taskComponentPanel=new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        //add scrolling to the task panel
        JScrollPane scrollPane=new JScrollPane(taskPanel);
        scrollPane.setBounds(8,70,Constraints.TASKPANEL_SIZE.width, Constraints.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(Constraints.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalScrollBar=scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);
        //add task button
        JButton addTaskButton =new JButton("ADD TASK");
        Font buttonFont = new Font("Times New Roman", Font.PLAIN, 16);
        addTaskButton.setFont(buttonFont);
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(-5,Constraints.GUI_SIZE.height-88,
                Constraints.ADDTASK_BUTTON_SIZE.width,Constraints.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        //add to frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

   @Override
    public void actionPerformed(ActionEvent e){
        String command=e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            //create a task component
            TaskComponent taskComponent=new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            if(taskComponentPanel.getComponentCount()>1){
                TaskComponent previousTask=(TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount()-2);
                previousTask.getTaskField().setBackground(null);
            }
            //make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
