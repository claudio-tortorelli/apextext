/*
 * AddCodeTemplate.java
 * Created on October 23, 2007, 11:34 AM 
 *
 * Copyright (C) 2008 Mrityunjoy Saha
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.apex.base.settings.ui;

import org.apex.base.data.AlphaNumericFilter;
import org.apex.base.settings.CodeTemplatesConfiguration;
import org.apex.base.settings.InvalidSettingsParameterException;
import org.apex.base.settings.SettingsMessageManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

/**
 * A form to add code templates. To add a code template abbreviation and
 * template code need to be entered by user.
 * @author Mrityunjoy Saha
 * @version 1.0
 * @since Apex 1.0
 */
public class AddCodeTemplate extends CodeTemplateChangeSupport {

    /** 
     * Creates new form {@code AddCodeTemplate} using specified code templates
     * configuration and a table which displays all code templates.
     * @param codeTemplatesConfiguration Code templates configuration.
     * @param codeTemplates The table which displays all available code templates.
     */
    public AddCodeTemplate(
            CodeTemplatesConfiguration codeTemplatesConfiguration,
            JTable codeTemplates) {
        super(codeTemplatesConfiguration, codeTemplates, null, null);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane11 = new javax.swing.JScrollPane();
        template = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        abbreviation = new javax.swing.JTextField();
        ((AbstractDocument)this.abbreviation.getDocument()).setDocumentFilter(new AlphaNumericFilter(20));

        template.setColumns(20);
        template.setRows(5);
        jScrollPane11.setViewportView(template);

        jLabel1.setText("Abbreviation:");

        jLabel3.setText("Template:");

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(abbreviation, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(379, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(abbreviation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancel)
                    .addComponent(ok))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        try {
            // Get entered abbreviation
            String enteredAbbreviation = this.abbreviation.getText();
            validateAbbreviation(enteredAbbreviation);
            // Get entered template
            String enteredTemplate = this.template.getText();
            validateTemplate(enteredTemplate);

            // Add the template
            this.codeTemplatesConfiguration.getCodeTemplates().
                    put(enteredAbbreviation, enteredTemplate);
            // Update the view
            ((DefaultTableModel) this.codeTemplates.getModel()).addRow(new String[]{
                        enteredAbbreviation,
                        enteredTemplate
                    });
            if (this.codeTemplates.getRowCount() > 0 &&
                    this.codeTemplates.getRowSelectionAllowed()) {
                this.codeTemplates.setRowSelectionInterval(this.codeTemplates.
                        getRowCount() -
                        1, this.codeTemplates.getRowCount() - 1);
            }
            closeWindow();
        } catch (InvalidSettingsParameterException ex) {
            SettingsMessageManager.showErrorMessage(this, ex.getErrorCode(),
                    ex.getPlaceHolders());
            this.abbreviation.requestFocus();
        }
}//GEN-LAST:event_okActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        closeWindow();
}//GEN-LAST:event_cancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abbreviation;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JButton ok;
    private javax.swing.JTextArea template;
    // End of variables declaration//GEN-END:variables
}
