
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

public class UpdateView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
	private JTextField name, no, species, color, age, owner, contact, breed;

	public UpdateView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.UPDATEVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(AppConstants.PET_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(AppConstants.PET_NO));
		no = new JTextField();
		jPanelCenter.add(no);
		jPanelCenter.add(new JLabel(AppConstants.PET_SPECIES));
		breed = new JTextField();
		jPanelCenter.add(breed);
		jPanelCenter.add(new JLabel(AppConstants.PET_BREED));
		species = new JTextField();
		jPanelCenter.add(species);
		jPanelCenter.add(new JLabel(AppConstants.PET_COLOR));
		color = new JTextField();
		jPanelCenter.add(color);
		jPanelCenter.add(new JLabel(AppConstants.PET_AGE));
		age = new JTextField();
		jPanelCenter.add(age);
		jPanelCenter.add(new JLabel(AppConstants.PET_OWNER));
		owner = new JTextField();
		jPanelCenter.add(owner);
		jPanelCenter.add(new JLabel(AppConstants.PET_CONTACT));
		contact = new JTextField();
		jPanelCenter.add(contact);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(AppConstants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Pet pe = new Pet();
					buildPet(pe);
					boolean isSuccess = ((PetDAO) BaseDAO.getAbilityDAO(DAO.PetDAO)).update(pe);
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
		jPanelSouth.add(updateButton);
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
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(no.getText()) || "".equals(species.getText())
				|| "".equals(breed.getText()) || "".equals(age.getText()) || "".equals(contact.getText())
				|| "".equals(owner.getText()) || "".equals(color.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildPet(Pet pe) {
		pe.setspecies(species.getText());
		pe.setowner(owner.getText());
		pe.setcolor(color.getText());
		pe.setage(age.getText());
		pe.setName(name.getText());
		pe.setno(no.getText());
		pe.setcontact(contact.getText());
		pe.setbreed(breed.getText());
	}

	private void setEmpty() {
		name.setText("");
		no.setText("");
		species.setText("");
		breed.setText("");
		owner.setText("");
		color.setText("");
		contact.setText("");
		age.setText("");
	}
}
