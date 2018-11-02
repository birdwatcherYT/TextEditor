package texteditor;
/////////////////////////////////////////////////////////////
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.datatransfer.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Iterator;
import java.awt.dnd.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/////////////////////////////////////////////////////////////

public class TextEditor extends JFrame implements ActionListener, KeyListener {

    String Array[];
    JLabel statusBar = new JLabel();
    int count = 0, size = 0, font = 0, tabS;
    JTextArea textArea;
    JComboBox select;
    JComboBox tab;
    JMenuBar menuBar;
    public JFileChooser chooser = new JFileChooser();
    JScrollPane scroll;
    JToolBar toolBar;
    String text;
    int WIDTH = 640, HEIGHT = 480;

    public static void main(String[] args) {
        new TextEditor();
    }
/////////////////////////////////////////////////////////////    

    TextEditor() {
        //title
        super("新規");
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setBounds(100, 100, WIDTH, HEIGHT);

        Array = new String[50];
        chooser.setFileFilter(new FileNameExtensionFilter("テキストファイル(*.txt)", "txt"));

        Array[0]="";
        
        //[x]で勝手に閉じないようにコントロール
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        menuBar();
        toolBar();
        textArea();
        info();
        setVisible(true);
        textArea.requestFocus();
    }
/////////////////////////////////////////////////////////////
    public void keyReleased(KeyEvent ke) {
        info();
    }
/////////////////////////////////////////////////////////////
    public void keyTyped(KeyEvent ke) {
        int key=ke.getKeyChar();
       /* if (key=='\n'||key==' '||key=='\b'||key=='.'||key==',') {
            rec();
        }*/
        if(!ke.isControlDown()){
            rec();
        }
    }
/////////////////////////////////////////////////////////////
    public void keyPressed(KeyEvent ke) {
        int key=ke.getKeyCode();
/*        if(ke.isControlDown() && (key==KeyEvent.VK_V || key==KeyEvent.VK_X)){
            rec();
        }
  */  }
/////////////////////////////////////////////////////////////

    void menuBar() {
        //menubar
        menuBar = new JMenuBar();
        //file
        JMenu menuFile = new JMenu("ファイル(F)");
        JMenuItem itemOpen = new JMenuItem("開く(O)");
        JMenuItem itemNew = new JMenuItem("新規(N)");
        JMenuItem itemClose = new JMenuItem("閉じる(X)");
        JMenuItem itemSave = new JMenuItem("上書き保存(S)");
        JMenuItem itemNewSave = new JMenuItem("名前を付けて保存(A)");

        itemOpen.setActionCommand("開く");
        itemNew.setActionCommand("新規");
        itemClose.setActionCommand("閉じる");
        itemSave.setActionCommand("上書き保存");
        itemNewSave.setActionCommand("名前を付けて保存");
        //view
        JMenu menuView = new JMenu("表示(V)");
        JMenu menuSize = new JMenu("画面サイズ(W)");
        JMenu menuFont = new JMenu("フォント(F)");
        JMenuItem itemBOLD = new JMenuItem("BOLD");
        JMenuItem itemPLAIN = new JMenuItem("PLAIN");
        JMenuItem itemITALIC = new JMenuItem("ITALIC");
        JMenuItem itemCENTER_BASELINE = new JMenuItem("CENTER_BASELINE");
        JMenuItem itemSet = new JMenuItem("文字サイズ(L)");
        JMenuItem itemTab = new JMenuItem("tabサイズ(B)");
        JMenuItem itemLarge = new JMenuItem("大");
        JMenuItem itemSmall = new JMenuItem("小");
        JMenuItem itemMiddle = new JMenuItem("中");
        JCheckBoxMenuItem checkToolBar = new JCheckBoxMenuItem("ツールバー(T)");
        JCheckBoxMenuItem checkStatusBar = new JCheckBoxMenuItem("ステータスバー(S)");
        JCheckBoxMenuItem checkWrap = new JCheckBoxMenuItem("右端で折り返す(R)");
        checkToolBar.setState(true);
        checkWrap.setState(true);
        checkStatusBar.setState(true);

        itemSet.setActionCommand("文字サイズ");
        itemTab.setActionCommand("tabサイズ");
        checkWrap.setActionCommand("右端で折り返す");
        checkStatusBar.setActionCommand("ステータスバー");
        checkToolBar.setActionCommand("ツールバー");
        //edit
        JMenu menuEdit = new JMenu("編集(E)");
        JMenuItem itemCut = new JMenuItem("切り取り(X)");
        JMenuItem itemCopy = new JMenuItem("コピー(C)");
        JMenuItem itemPaste = new JMenuItem("貼り付け(P)");
        JMenuItem itemCap = new JMenuItem("すべて大文字に変換(U)");
        JMenuItem itemLow = new JMenuItem("すべて小文字に変換(L)");
        JMenuItem itemHalf = new JMenuItem("アルファベットを半角に変換(H)");
        JMenuItem itemWide = new JMenuItem("アルファベットを全角に変換(W)");
        JMenuItem itemBack = new JMenuItem("元に戻す(B)");
        JMenuItem itemNext = new JMenuItem("やり直し(N)");
        JMenuItem itemFind = new JMenuItem("検索(F)");

        itemCut.setActionCommand("切り取り");
        itemCopy.setActionCommand("コピー");
        itemPaste.setActionCommand("貼り付け");
        itemCap.setActionCommand("すべて大文字に変換");
        itemLow.setActionCommand("すべて小文字に変換");
        itemHalf.setActionCommand("アルファベットを半角に変換");
        itemWide.setActionCommand("アルファベットを全角に変換");
        itemBack.setActionCommand("元に戻す");
        itemNext.setActionCommand("やり直し");
        itemFind.setActionCommand("検索");
        //keybord
        menuFile.setMnemonic('F');
        itemOpen.setMnemonic('O');
        itemNew.setMnemonic('N');
        itemNewSave.setMnemonic('A');
        itemClose.setMnemonic('X');
        itemSave.setMnemonic('S');
        menuView.setMnemonic('V');
        checkToolBar.setMnemonic('T');
        itemSet.setMnemonic('L');
        itemTab.setMnemonic('B');
        checkWrap.setMnemonic('R');
        checkStatusBar.setMnemonic('S');
        menuSize.setMnemonic('W');
        menuFont.setMnemonic('F');

        menuEdit.setMnemonic('E');
        itemCut.setMnemonic('X');
        itemCopy.setMnemonic('C');
        itemPaste.setMnemonic('P');
        itemCap.setMnemonic('U');
        itemLow.setMnemonic('L');
        itemHalf.setMnemonic('H');
        itemWide.setMnemonic('W');
        itemBack.setMnemonic('B');
        itemNext.setMnemonic('N');
        itemFind.setMnemonic('F');

        //listener
        itemOpen.addActionListener(this);
        itemClose.addActionListener(this);
        itemSave.addActionListener(this);
        itemNewSave.addActionListener(this);
        itemNew.addActionListener(this);
        checkToolBar.addActionListener(this);
        itemLarge.addActionListener(this);
        itemSmall.addActionListener(this);
        itemMiddle.addActionListener(this);
        checkWrap.addActionListener(this);
        checkStatusBar.addActionListener(this);
        itemCut.addActionListener(this);
        itemCopy.addActionListener(this);
        itemPaste.addActionListener(this);
        itemSet.addActionListener(this);
        itemTab.addActionListener(this);
        itemCap.addActionListener(this);
        itemLow.addActionListener(this);
        itemHalf.addActionListener(this);
        itemWide.addActionListener(this);
        itemBack.addActionListener(this);
        itemNext.addActionListener(this);
        itemFind.addActionListener(this);
        itemBOLD.addActionListener(this);
        itemPLAIN.addActionListener(this);
        itemITALIC.addActionListener(this);
        itemCENTER_BASELINE.addActionListener(this);
        //add
        menuBar.add(menuFile);
        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.add(itemNewSave);
        menuFile.add(itemSave);
        menuFile.add(itemClose);
        menuBar.add(menuEdit);
        menuEdit.add(itemCut);
        menuEdit.add(itemCopy);
        menuEdit.add(itemPaste);
        menuEdit.add(itemBack);
        menuEdit.add(itemNext);
        menuEdit.add(itemCap);
        menuEdit.add(itemLow);
        menuEdit.add(itemHalf);
        menuEdit.add(itemWide);
        menuEdit.add(itemFind);
        menuBar.add(menuView);
        menuView.add(checkToolBar);
        menuView.add(checkStatusBar);
        menuView.add(checkWrap);
        menuView.add(itemSet);
        menuView.add(menuFont);
        menuFont.add(itemBOLD);
        menuFont.add(itemPLAIN);
        menuFont.add(itemITALIC);
        menuFont.add(itemCENTER_BASELINE);
        menuView.add(itemTab);
        menuView.add(menuSize);
        menuSize.add(itemLarge);
        menuSize.add(itemMiddle);
        menuSize.add(itemSmall);
        //keyaction
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        itemNewSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
        checkToolBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
        itemBack.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
        itemNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
        //set
        setJMenuBar(menuBar);
    }
/////////////////////////////////////////////////////////////
    void textArea() {
        //textarea
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        size = 12;
        font = 1;
        text = "";
        tabS = 10;
        textArea.addKeyListener(this);
        textArea.setTabSize(10);
        scroll = new JScrollPane(textArea);
        add("Center", scroll);
        setTextSizeFont(size, font);
        setTab(tabS);
        new DropTarget(textArea, new Drop());
    }
/////////////////////////////////////////////////////////////
    void setTextSizeFont(int i, int f) {
        switch (f) {
            case 0:
                f = Font.BOLD;
                break;
            case 1:
                f = Font.PLAIN;
                break;
            case 2:
                f = Font.ITALIC;
                break;
            case 3:
                f = Font.CENTER_BASELINE;
                break;
        }
        textArea.setFont(new Font("", f, i));
        add(scroll);
    }
/////////////////////////////////////////////////////////////    
    void setTab(int i) {
        textArea.setTabSize(i);
        add(scroll);
    }
/////////////////////////////////////////////////////////////    
    void toolBar() {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setLayout(new GridLayout());
        //button
        JButton bNew = new JButton("新規");
        JButton bOpen = new JButton("開く");
        JButton bSave = new JButton("上書き保存");
        JButton bClose = new JButton("閉じる");
        //label
        JLabel l1 = new JLabel("文字サイズ");
        JLabel l2 = new JLabel("tabサイズ");
        //select
        select = new JComboBox();
        select.addItem("8");
        select.addItem("12");
        select.addItem("20");
        select.addItem("30");
        select.addItem("40");
        select.addItem("任意");
        select.setSelectedIndex(1);
        select.setActionCommand("文字");
        tab = new JComboBox();
        tab.addItem("3");
        tab.addItem("5");
        tab.addItem("8");
        tab.addItem("10");
        tab.addItem("15");
        tab.addItem("任意");
        tab.setSelectedIndex(3);
        tab.setActionCommand("タブ");
        //listener
        bNew.addActionListener(this);
        bOpen.addActionListener(this);
        bSave.addActionListener(this);
        bClose.addActionListener(this);
        select.addActionListener(this);
        tab.addActionListener(this);
        //add
        toolBar.add(bNew);
        toolBar.add(bOpen);
        toolBar.add(bSave);
        toolBar.add(l1);
        toolBar.add(select);
        toolBar.add(l2);
        toolBar.add(tab);
        toolBar.add(bClose);
        add("North", toolBar);
    }
/////////////////////////////////////////////////////////////    
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "開く":
                open();
                break;
            case "新規":
                newf();
                break;
            case "上書き保存":
                save();
                break;
            case "名前を付けて保存":
                newSave();
                break;
            case "閉じる":
                close();
                break;
            case "ツールバー":
                toolBar.setVisible(((JCheckBoxMenuItem) ae.getSource()).getState());
                break;
            case "大":
                setSize(800, 600);
                break;
            case "中":
                setSize(640, 480);
                break;
            case "小":
                setSize(320, 240);
                break;
            case "右端で折り返す":
                textArea.setLineWrap(((JCheckBoxMenuItem) ae.getSource()).getState());
                break;
            case "文字":
                switch (select.getSelectedIndex()) {
                    case 0:
                        size = 8;
                        break;
                    case 1:
                        size = 12;
                        break;
                    case 2:
                        size = 20;
                        break;
                    case 3:
                        size = 30;
                        break;
                    case 4:
                        size = 40;
                        break;
                    case 5:
                        String str = JOptionPane.showInputDialog(this, "文字サイズを入力してください");

                        try {
                            size = Integer.parseInt(str);
                            if (str != null && !str.equals("") && size > 0) {
                                select.setSelectedIndex(5);
                            }
                        } catch (NumberFormatException e) {
                        }
                        break;
                }
                setTextSizeFont(size, font);
                break;
            case "タブ":
                switch (tab.getSelectedIndex()) {
                    case 0:
                        tabS = 3;
                        break;
                    case 1:
                        tabS = 5;
                        break;
                    case 2:
                        tabS = 8;
                        break;
                    case 3:
                        tabS = 10;
                        break;
                    case 4:
                        tabS = 15;
                        break;
                    case 5:
                        String str = JOptionPane.showInputDialog(this, "tabサイズを入力してください");
                        try {
                            tabS = Integer.parseInt(str);
                            if (str != null && !str.equals("") && tabS > 0) {

                                tab.setSelectedIndex(5);
                            }
                        } catch (NumberFormatException e) {
                        }
                        break;
                }
                setTab(tabS);
                break;
            case "コピー":
                copy();
                break;
            case "切り取り":
                rec();
                cut();
                break;
            case "貼り付け":
                rec();
                paste();
                break;
            case "文字サイズ":
                select.setSelectedIndex(5);
                break;
            case "tabサイズ":
                tab.setSelectedIndex(5);
                break;
            case "すべて大文字に変換":
                rec();
                capital();
                break;
            case "すべて小文字に変換":
                rec();
                small();
                break;
            case "元に戻す":
                textArea.setText(back());
                break;
            case "やり直し":
                textArea.setText(next());
                break;
            case "検索":
                String str = "";
                str = JOptionPane.showInputDialog(this, "検索ワードを入力してください\nカーソルの位置以降で最初に一致した位置に移動します");
                if (str != null) {
                    find(str, textArea.getCaretPosition());
                }
                break;
            case "ステータスバー":
                statusBar.setVisible(((JCheckBoxMenuItem) ae.getSource()).getState());
                break;
            case "CENTER_BASELINE":
                font = 3;
                setTextSizeFont(size, font);
                break;
            case "ITALIC":
                font = 2;
                setTextSizeFont(size, font);
                break;
            case "PLAIN":
                font = 1;
                setTextSizeFont(size, font);
                break;
            case "BOLD":
                font = 0;
                setTextSizeFont(size, font);
                break;
            case "アルファベットを半角に変換":
                rec();
                half();
                break;
            case "アルファベットを全角に変換":
                rec();
                wide();
                break;
        }
        info();
    }
    
////////////////////////////////////////////////////////////
    void rec() {
        Array[count] = textArea.getText();
        count++;
        if (count % 50 == 0) {
            String temp[] = new String[count + 50];
            System.arraycopy(Array, 0, temp, 0, Array.length);
            Array = temp;
        }
    }
/////////////////////////////////////////////////////////////    
    void find(String str, int n) {
        int index;
        if ((index = textArea.getText().indexOf(str, n)) != -1) {
            JOptionPane.showMessageDialog(this, (index + 1) + "文字目に見つかりました\nカーソルを移動させます\nもう一度検索をかければこれ以降で最初に一致した位置に移動できます");
            textArea.setCaretPosition(index);
        } else {
            JOptionPane.showMessageDialog(this, "見つかりませんでした");
        }
    }
///////////////////////////////////////////////////////////////
    void half() {
        for (int i = 0; i < 26; i++) {
            textArea.setText(textArea.getText().replace((char) ('ａ' + i), (char) ('a' + i)));
            textArea.setText(textArea.getText().replace((char) ('Ａ' + i), (char) ('A' + i)));
        }
    }
////////////////////////////////////////////////////////////////
    void wide() {
        for (int i = 0; i < 26; i++) {
            textArea.setText(textArea.getText().replace((char) ('a' + i), (char) ('ａ' + i)));
            textArea.setText(textArea.getText().replace((char) ('A' + i), (char) ('Ａ' + i)));
        }
    }
/////////////////////////////////////////////////////////////    
    String next() {
        count++;
        if (Array[count] == null) {
            JOptionPane.showMessageDialog(this, "これ以上やり直せません");
            count--;
            return textArea.getText();
        }
        return Array[count];
    }
/////////////////////////////////////////////////////////////
    String back() {
        count--;
        if (count < 0) {
            count = 0;
        }
        if (Array[count] == null) {
            JOptionPane.showMessageDialog(this, "これ以上戻れません");
            count++;
            return textArea.getText();
        }
        return Array[count];
    }
/////////////////////////////////////////////////////////////    
    void capital() {
        textArea.setText(textArea.getText().toUpperCase());
    }
/////////////////////////////////////////////////////////////    

    void small() {
        textArea.setText(textArea.getText().toLowerCase());
    }
/////////////////////////////////////////////////////////////    

    void copy() {
        Clipboard cb = getToolkit().getSystemClipboard();
        StringSelection strSel = new StringSelection(textArea.getSelectedText());
        cb.setContents(strSel, strSel);
    }
/////////////////////////////////////////////////////////////    

    void paste() {
        Clipboard cb = getToolkit().getSystemClipboard();
        Transferable data = cb.getContents(this);
        if (data != null && data.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                textArea.replaceSelection((String) data.getTransferData(DataFlavor.stringFlavor));
            } catch (UnsupportedFlavorException e) {
            } catch (IOException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////    

    void cut() {
        copy();
        textArea.replaceSelection("");
    }
/////////////////////////////////////////////////////////////    

    int close() {
        int flag;
        if (!text.equals(textArea.getText())) {
            flag = message();
            if (flag == JOptionPane.YES_OPTION) {
                int value = save();
                if (value != JFileChooser.APPROVE_OPTION) {
                    return 0;
                }
            } else if (flag == JOptionPane.NO_OPTION) {
            } else {
                return 0;
            }
        }
        System.exit(0);
        return 0;
    }
/////////////////////////////////////////////////////////////    

    int newf() {
        File temp = chooser.getSelectedFile();
        int flag = JOptionPane.NO_OPTION;
        if (!text.equals(textArea.getText())) {
            flag = message();
        }
        if (flag == JOptionPane.YES_OPTION) {
            int value = save();
            if (value != JFileChooser.APPROVE_OPTION) {
                chooser.setSelectedFile(temp);
                return 0;
            }
        } else if (flag == JOptionPane.NO_OPTION) {
        } else {
            return 0;
        }
        chooser.setSelectedFile(null);
        setTitle("新規");
        textArea.setText("");
        text = textArea.getText();
        textArea.requestFocus();
        return 0;
    }
/////////////////////////////////////////////////////////////    

    int message() {
        String str = chooser.getName(chooser.getSelectedFile());
        if (str == null) {
            str = "新規";
        }
        int flag = JOptionPane.showConfirmDialog(this, str + "の変更を保存しますか？", "確認", JOptionPane.YES_NO_CANCEL_OPTION);
        return flag;
    }
/////////////////////////////////////////////////////////////    

    int open() {
        File temp = chooser.getSelectedFile();
        int flag;
        if (!text.equals(textArea.getText())) {
            flag = message();
            if (flag == JOptionPane.YES_OPTION) {
                int value = save();
                if (value != JFileChooser.APPROVE_OPTION) {
                    chooser.setSelectedFile(temp);
                    return 0;
                }
            } else if (flag == JOptionPane.CANCEL_OPTION) {
                chooser.setSelectedFile(temp);
                return 0;
            } else {
                chooser.setSelectedFile(temp);
            }
        }
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader read = new FileReader(chooser.getSelectedFile());
                textArea.read(read, null);
                setTitle(chooser.getName(chooser.getSelectedFile()));
                read.close();
                text = textArea.getText();
            } catch (FileNotFoundException fnfe) {
            } catch (IOException ioe) {
            }
        } else {
            chooser.setSelectedFile(temp);
        }
        textArea.requestFocus();
        return 0;
    }
/////////////////////////////////////////////////////////////    

    int save() {
        int value = 0;

        if (chooser.getSelectedFile() == null) {
            value = newSave();
        } else {
            try {
                FileWriter write = new FileWriter(chooser.getSelectedFile());
                write.write(textArea.getText());
                write.close();
                setTitle(chooser.getName(chooser.getSelectedFile()));
                text = textArea.getText();
            } catch (Exception e) {
            }
        }
        textArea.requestFocus();
        return value;
    }
/////////////////////////////////////////////////////////////    

    void info() {
        statusBar.setText(String.valueOf(textArea.getLineCount()) + "行" + String.valueOf(textArea.getText().length()) + "文字");
        add("South", statusBar);
    }
/////////////////////////////////////////////////////////////    

    int newSave() {

        File temp = chooser.getSelectedFile();
        int value;
        if ((value = chooser.showSaveDialog(this)) == JFileChooser.APPROVE_OPTION) {
            try {
                String path = chooser.getSelectedFile().getPath();
                if (chooser.getFileFilter().toString().contains("txt") && !(path.substring(path.lastIndexOf('\\'))).contains(".")) {
                    path += ".txt";
                    chooser.setSelectedFile(new File(path));
                }
                if (new File(path).exists()) {
                    int flag = JOptionPane.showConfirmDialog(this, "同一名ファイルが存在します\n上書きしてよろしいですか？", "確認", JOptionPane.YES_NO_OPTION);
                    if (flag == JOptionPane.OK_OPTION) {
                        FileWriter write = new FileWriter(chooser.getSelectedFile());
                        write.write(textArea.getText());
                        write.close();
                        setTitle(chooser.getName(chooser.getSelectedFile()));
                        text = textArea.getText();
                    } else {
                        chooser.setSelectedFile(temp);
                    }
                } else {
                    FileWriter write = new FileWriter(chooser.getSelectedFile());
                    write.write(textArea.getText());
                    write.close();
                    setTitle(chooser.getName(chooser.getSelectedFile()));
                    text = textArea.getText();
                }
            } catch (Exception e) {
            }
        } else {
            chooser.setSelectedFile(temp);
        }
        textArea.requestFocus();
        return value;
    }
    ///////////////////////////////////////

    class Drop extends DropTargetAdapter {

        String str1, str2;

        Drop() {
        }

        public void drop(DropTargetDropEvent e) {
            try {
                Transferable transfer = e.getTransferable();
                if (transfer.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    java.util.List fileList = (java.util.List) transfer.getTransferData(DataFlavor.javaFileListFlavor);
                    Iterator iterator = fileList.iterator();
                    File temp = chooser.getSelectedFile();
                    int flag;
                    if (!text.equals(textArea.getText())) {
                        flag = message();
                        if (flag == JOptionPane.YES_OPTION) {
                            int value = save();
                            if (value != JFileChooser.APPROVE_OPTION) {
                                chooser.setSelectedFile(temp);
                                return;
                            }
                        } else if (flag == JOptionPane.CANCEL_OPTION) {
                            chooser.setSelectedFile(temp);
                            return;
                        } else {
                            chooser.setSelectedFile(temp);
                        }
                    }
                    while (iterator.hasNext()) {
                        try {
                            File fp = (File) iterator.next();
                            FileReader read = new FileReader(fp);
                            textArea.read(read, null);
                            setTitle(fp.getName());
                            read.close();
                            text = textArea.getText();
                            chooser.setSelectedFile(fp);
                        } catch (Exception ex) {
                        }
                    }
                }
            } catch (UnsupportedFlavorException ufe) {
            } catch (IOException ioe) {
            }
        }
    }
}
