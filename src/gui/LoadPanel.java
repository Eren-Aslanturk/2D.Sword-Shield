package gui;

import logic.GameManager;
import util.FileManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Element;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class LoadPanel extends JPanel {

    private JList list;
    private JButton selectButton, removeButton, loadButton;
    private JLabel loadText;
    private DefaultListModel model, myModel;
    private JScrollPane pane;
    private ArrayList<String> savedGames;
    private MenuButton backButton;
    private BufferedImage backgroundImage;
    private int listCount = 0;

    private Object[] listItems = new JButton[5];

    private JButton north,south,east,west,center;

    public LoadPanel(){
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        north = new JButton();
        south = new JButton();
        east = new JButton();
        west = new JButton();
        center = new JButton("Selection");

        //border slots
        north.setPreferredSize(new Dimension(800,200));
        north.setIcon(new ImageIcon(getBackgroundImage()));

        south.setPreferredSize(new Dimension(800,200));
        south.setIcon(new ImageIcon(getBackgroundImage()));

        east.setPreferredSize(new Dimension(300,600));
        east.setIcon(new ImageIcon(getBackgroundImage()));

        west.setPreferredSize(new Dimension(300,600));
        west.setIcon(new ImageIcon(getBackgroundImage()));

        center.setPreferredSize(new Dimension(300,600));
        //center.setIcon(new ImageIcon(getBackgroundImage()));

        center.addActionListener((ActionEvent e) -> {
            int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();


            JFrame listFrame = new JFrame();
            listFrame.setVisible(true);
            listFrame.setBounds(getX()+2*screenWidth/5 + 62,getY()+screenHeight/3,225,250);


            listFrame.add(ListModelConstructor());
/*
            for(int i=0;i<listItems.length;i++){
                listItems[i]=new JButton("savedata"+i);
            }

            myModel = new DefaultListModel();

            for(int i=0;i<listItems.length;i++){
                myModel.addElement(listItems[i]);
            }
            list = new JList(myModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            JScrollPane listScroller = new JScrollPane(list);
            listScroller.setPreferredSize(new Dimension(250, 80));

            listFrame.add(listScroller);
            */





        });

        backButton = new MenuButton(370,500, FileManager.getInstance().getImage("/images/credits/back.png"),
                FileManager.getInstance().getImage("/images/credits/backhover.png"));
        backButton.addActionListener(back -> {
            GUIManager.getInstance().showMenuPanel();
        });

        south.add(backButton);

        //add slots to border
        add(north,BorderLayout.NORTH);
        add(south,BorderLayout.SOUTH);
        add(east,BorderLayout.EAST);
        add(west,BorderLayout.WEST);
        add(center,BorderLayout.CENTER);



        // add(loadText, BorderLayout.NORTH);

        // add(selectButton, BorderLayout.CENTER);
        //  add(removeButton, BorderLayout.WEST);



        // add(backButton, BorderLayout.SOUTH);


    }
    // TODO some of required methods will be passed LoadPanel
    public void Operation() {
        setLayout(new BorderLayout());
        loadText = new JLabel("Load");
        model = new DefaultListModel();
        list = new JList(model);
        pane = new JScrollPane(list);
        savedGames = FileManager.getSavedGames();

        for (String savedGame : savedGames)
            model.addElement(savedGame);

        selectButton = new JButton("Select");
        selectButton.addActionListener( select -> {
            // TODO when not serializable exception in save function is solved, continue here
            GUIManager loadInstance = GUIManager.getInstance(); // loadInstance instance will be the file loaded
            try {
                FileInputStream fileIn = new FileInputStream("savedata0.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                // GUIManager loadInstance = (GUIManager) in.readObject();
                GUIManager.setInstance(loadInstance).showGamePanel();

                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            }

            //GUIManager.getInstance().showGamePanel();
/*
            InputStream in = getClass().getClassLoader().getResourceAsStream("savedata0.ser");

            if ( in == null ) try {
                throw new FileNotFoundException("resource not found: " + "savedata0.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
*/
        });

        removeButton = new JButton( "Remove");
        removeButton.addActionListener( remove -> {

        });

        add(pane, BorderLayout.NORTH);
        // add(pane, BorderLayout.CENTER);
        add(selectButton, BorderLayout.EAST);
        add(removeButton, BorderLayout.WEST);

        backgroundImage = FileManager.getInstance().getImage("/images/menu/menu_bg.gif");
        backButton = new MenuButton(370,500, FileManager.getInstance().getImage("/images/credits/back.png"),
                FileManager.getInstance().getImage("/images/credits/backhover.png"));
        backButton.addActionListener(back -> {
            GUIManager.getInstance().showMenuPanel();
        });
        add(backButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(800, 600));
    }

    //check if a file is available
    private boolean checkFileExists(){
        return FileManager.getInstance()!=null;

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0, null);
    }

    private void addElement(Object[] mylist){
        list = new JList(mylist);
    }

    private void prepareArrayList(){
        for(int i=0;i<listItems.length;i++)
            listItems[i]=new JButton("Savedata"+i);

        for(int i=0;i<listItems.length;i++){
            myModel.addElement(listItems[i]);
        }

    }

    private BufferedImage getBackgroundImage(){
        return FileManager.getInstance().getImage("/images/menu/menu_bg.gif");

    }

    class PanelRenderer implements ListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JButton renderer = (JButton) value;
            //  renderer.setBackground(isSelected ? Color.red : list.getBackground());
            return renderer;
        }
    }

    private JPanel ListModelConstructor(){
        //TODO semi-given example, will be improved for the content
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BorderLayout());
        DefaultListModel myModel = new DefaultListModel();
        JList mylist = new JList(myModel);
        JScrollPane pane = new JScrollPane(mylist);
        JButton myloadButton = new JButton("Load");
        JButton myremoveButton = new JButton("Remove");



        for (int i = 0; i < 15; i++) {
            myModel.addElement("Savedata " + i);
            updateCount();
        }


        //load button
        myloadButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println(getCount());
                if(getCount()>0){
                    System.out.println(myModel.get(selectedItemID()));

                }

                System.out.println("no file found");



                /*
                //TODO add item -> usable for to save a serialized file
                int counter = 15;
                public void actionPerformed(ActionEvent e) {
                myModel.addElement("Savedata " + getCount());
                updateCount();
                }
                 */
            }
        });

        //remove button
        myremoveButton.addActionListener(e -> {
            if (myModel.getSize() > 0) {
                myModel.removeElementAt(0);
                downdateCount();

            }

        });

        myPanel.add(pane, BorderLayout.NORTH);
        myPanel.add(myloadButton, BorderLayout.WEST);
        myPanel.add(myremoveButton, BorderLayout.EAST);

        myPanel.setPreferredSize(new Dimension(200,400));

        return myPanel; //return content
    }

    private Element selectedItem(String fileName){
        for(int i=0;i<myModel.size();i++){

        }
        System.out.println("File is not found");
        return null;
    }

    private int selectedItemID(){

        return 4;

    }

    public int getCount(){
        System.out.println(listCount);
        return listCount;

    }

    private void updateCount(){

        listCount++;
        System.out.println(listCount);
    }

    private void downdateCount(){

        listCount--;
        System.out.println(listCount);
    }
}
