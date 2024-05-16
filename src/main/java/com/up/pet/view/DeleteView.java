package com.up.pet.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.up.pet.AppConstants;
import com.up.pet.DAO;
import com.up.pet.base.BaseDAO;
import com.up.pet.dao.PetDAO;
import com.up.pet.model.Pet;

public class DeleteView extends JFrame {

	private static final long serialVersionUID = -7668153283910203959L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField name, no;

	public DeleteView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.DELETEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(AppConstants.PET_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(AppConstants.PET_NO));
		no = new JTextField();
		jPanelCenter.add(no);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton(AppConstants.DELETEVIEW_DELETEBUTTON);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Pet pe = new Pet();
					buildPet(pe);
					boolean isSuccess = ((PetDAO) BaseDAO.getAbilityDAO(DAO.PetDAO)).delete(pe);
					if (isSuccess) {
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((PetDAO) BaseDAO.getAbilityDAO(DAO.PetDAO))
								.list(MainView.currPageNum);
						MainView.initJTable(MainView.jTable, result);
					}
				}
			}
		});
		jPanelSouth.add(deleteButton);
		exitButton = new JButton(AppConstants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 250, 400, 130);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(no.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildPet(Pet pe) {
		pe.setName(name.getText());
		pe.setno(no.getText());
	}

	private void setEmpty() {
		name.setText("");
		no.setText("");
	}

}
