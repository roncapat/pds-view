# Copyright (c) 2019, California Institute of Technology ("Caltech").  
# U.S. Government sponsorship acknowledged.
#
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
#
# * Redistributions of source code must retain the above copyright notice,
#   this list of conditions and the following disclaimer.
# * Redistributions must reproduce the above copyright notice, this list of
#   conditions and the following disclaimer in the documentation and/or other
#   materials provided with the distribution.
# * Neither the name of Caltech nor its operating division, the Jet Propulsion
#   Laboratory, nor the names of its contributors may be used to endorse or
#   promote products derived from this software without specific prior written
#   permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
# AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
# ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
# LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
# INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
# CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
# ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
# POSSIBILITY OF SUCH DAMAGE.

# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'searchTableDialog.ui'
#
# Created by: PyQt4 UI code generator 4.12
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    def _fromUtf8(s):
        return s

try:
    _encoding = QtGui.QApplication.UnicodeUTF8
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig, _encoding)
except AttributeError:
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig)

class Ui_SearchTableDialog(object):
    def setupUi(self, SearchTableDialog):
        SearchTableDialog.setObjectName(_fromUtf8("SearchTableDialog"))
        SearchTableDialog.setWindowModality(QtCore.Qt.NonModal)
        SearchTableDialog.resize(466, 550)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(1)
        sizePolicy.setVerticalStretch(1)
        sizePolicy.setHeightForWidth(SearchTableDialog.sizePolicy().hasHeightForWidth())
        SearchTableDialog.setSizePolicy(sizePolicy)
        SearchTableDialog.setMaximumSize(QtCore.QSize(10000, 10000))
        font = QtGui.QFont()
        font.setFamily(_fromUtf8("Helvetica"))
        font.setPointSize(14)
        font.setBold(False)
        font.setItalic(False)
        font.setWeight(50)
        SearchTableDialog.setFont(font)
        SearchTableDialog.setContextMenuPolicy(QtCore.Qt.DefaultContextMenu)
        self.gridLayout = QtGui.QGridLayout(SearchTableDialog)
        self.gridLayout.setObjectName(_fromUtf8("gridLayout"))
        self.value_label = QtGui.QLabel(SearchTableDialog)
        self.value_label.setObjectName(_fromUtf8("value_label"))
        self.gridLayout.addWidget(self.value_label, 1, 0, 1, 1)
        self.search_button = QtGui.QPushButton(SearchTableDialog)
        self.search_button.setObjectName(_fromUtf8("search_button"))
        self.gridLayout.addWidget(self.search_button, 8, 6, 1, 1)
        self.end_value_line_edit = QtGui.QLineEdit(SearchTableDialog)
        self.end_value_line_edit.setToolTip(_fromUtf8(""))
        self.end_value_line_edit.setObjectName(_fromUtf8("end_value_line_edit"))
        self.gridLayout.addWidget(self.end_value_line_edit, 5, 1, 1, 2)
        self.start_value_line_edit = QtGui.QLineEdit(SearchTableDialog)
        self.start_value_line_edit.setToolTip(_fromUtf8(""))
        self.start_value_line_edit.setObjectName(_fromUtf8("start_value_line_edit"))
        self.gridLayout.addWidget(self.start_value_line_edit, 4, 1, 1, 2)
        self.distribution_button = QtGui.QPushButton(SearchTableDialog)
        self.distribution_button.setToolTip(_fromUtf8(""))
        self.distribution_button.setObjectName(_fromUtf8("distribution_button"))
        self.gridLayout.addWidget(self.distribution_button, 5, 5, 1, 2)
        self.end_value_label = QtGui.QLabel(SearchTableDialog)
        self.end_value_label.setObjectName(_fromUtf8("end_value_label"))
        self.gridLayout.addWidget(self.end_value_label, 5, 0, 1, 1)
        self.cancel_button = QtGui.QPushButton(SearchTableDialog)
        self.cancel_button.setObjectName(_fromUtf8("cancel_button"))
        self.gridLayout.addWidget(self.cancel_button, 8, 5, 1, 1)
        self.start_value_label = QtGui.QLabel(SearchTableDialog)
        self.start_value_label.setObjectName(_fromUtf8("start_value_label"))
        self.gridLayout.addWidget(self.start_value_label, 4, 0, 1, 1)
        self.for_mixed_tables_label = QtGui.QLabel(SearchTableDialog)
        self.for_mixed_tables_label.setObjectName(_fromUtf8("for_mixed_tables_label"))
        self.gridLayout.addWidget(self.for_mixed_tables_label, 0, 5, 1, 2)
        self.results_textEdit = QtGui.QTextEdit(SearchTableDialog)
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(1)
        sizePolicy.setVerticalStretch(1)
        sizePolicy.setHeightForWidth(self.results_textEdit.sizePolicy().hasHeightForWidth())
        self.results_textEdit.setSizePolicy(sizePolicy)
        self.results_textEdit.setMaximumSize(QtCore.QSize(9000, 9000))
        self.results_textEdit.setReadOnly(True)
        self.results_textEdit.setObjectName(_fromUtf8("results_textEdit"))
        self.gridLayout.addWidget(self.results_textEdit, 7, 0, 1, 7)
        self.comboBox = QtGui.QComboBox(SearchTableDialog)
        self.comboBox.setToolTip(_fromUtf8(""))
        self.comboBox.setObjectName(_fromUtf8("comboBox"))
        self.comboBox.addItem(_fromUtf8(""))
        self.comboBox.addItem(_fromUtf8(""))
        self.comboBox.addItem(_fromUtf8(""))
        self.gridLayout.addWidget(self.comboBox, 1, 5, 1, 2)
        self.value_line_edit = QtGui.QLineEdit(SearchTableDialog)
        font = QtGui.QFont()
        font.setItalic(False)
        self.value_line_edit.setFont(font)
        self.value_line_edit.setToolTip(_fromUtf8(""))
        self.value_line_edit.setObjectName(_fromUtf8("value_line_edit"))
        self.gridLayout.addWidget(self.value_line_edit, 1, 1, 1, 2)
        self.type_label = QtGui.QLabel(SearchTableDialog)
        self.type_label.setObjectName(_fromUtf8("type_label"))
        self.gridLayout.addWidget(self.type_label, 1, 4, 1, 1)
        self.locations_button = QtGui.QPushButton(SearchTableDialog)
        self.locations_button.setToolTip(_fromUtf8(""))
        self.locations_button.setObjectName(_fromUtf8("locations_button"))
        self.gridLayout.addWidget(self.locations_button, 3, 5, 2, 2)
        self.find_range_label = QtGui.QLabel(SearchTableDialog)
        self.find_range_label.setObjectName(_fromUtf8("find_range_label"))
        self.gridLayout.addWidget(self.find_range_label, 3, 1, 1, 1)
        self.horizontal_line_top = QtGui.QFrame(SearchTableDialog)
        self.horizontal_line_top.setFrameShape(QtGui.QFrame.HLine)
        self.horizontal_line_top.setFrameShadow(QtGui.QFrame.Sunken)
        self.horizontal_line_top.setObjectName(_fromUtf8("horizontal_line_top"))
        self.gridLayout.addWidget(self.horizontal_line_top, 2, 0, 1, 7)
        self.find_single_value_label = QtGui.QLabel(SearchTableDialog)
        self.find_single_value_label.setObjectName(_fromUtf8("find_single_value_label"))
        self.gridLayout.addWidget(self.find_single_value_label, 0, 1, 1, 2)
        self.vertical_line = QtGui.QFrame(SearchTableDialog)
        self.vertical_line.setFrameShape(QtGui.QFrame.VLine)
        self.vertical_line.setFrameShadow(QtGui.QFrame.Sunken)
        self.vertical_line.setObjectName(_fromUtf8("vertical_line"))
        self.gridLayout.addWidget(self.vertical_line, 0, 3, 2, 1)
        self.results_label = QtGui.QLabel(SearchTableDialog)
        self.results_label.setObjectName(_fromUtf8("results_label"))
        self.gridLayout.addWidget(self.results_label, 6, 2, 1, 2, QtCore.Qt.AlignHCenter)
        self.value_label.raise_()
        self.type_label.raise_()
        self.comboBox.raise_()
        self.horizontal_line_top.raise_()
        self.find_single_value_label.raise_()
        self.find_range_label.raise_()
        self.start_value_label.raise_()
        self.end_value_label.raise_()
        self.start_value_line_edit.raise_()
        self.value_line_edit.raise_()
        self.end_value_line_edit.raise_()
        self.vertical_line.raise_()
        self.for_mixed_tables_label.raise_()
        self.locations_button.raise_()
        self.distribution_button.raise_()
        self.cancel_button.raise_()
        self.search_button.raise_()
        self.results_textEdit.raise_()
        self.results_label.raise_()

        self.retranslateUi(SearchTableDialog)
        QtCore.QMetaObject.connectSlotsByName(SearchTableDialog)

    def retranslateUi(self, SearchTableDialog):
        SearchTableDialog.setWindowTitle(_translate("SearchTableDialog", "Find and Highlight Values in Table", None))
        self.value_label.setText(_translate("SearchTableDialog", "Value", None))
        self.search_button.setText(_translate("SearchTableDialog", "Search", None))
        self.distribution_button.setText(_translate("SearchTableDialog", "Distribution", None))
        self.end_value_label.setText(_translate("SearchTableDialog", "End Value", None))
        self.cancel_button.setText(_translate("SearchTableDialog", "Done", None))
        self.start_value_label.setText(_translate("SearchTableDialog", "Start Value", None))
        self.for_mixed_tables_label.setText(_translate("SearchTableDialog", "For Mixed Tables", None))
        self.comboBox.setItemText(0, _translate("SearchTableDialog", "Integer", None))
        self.comboBox.setItemText(1, _translate("SearchTableDialog", "Float", None))
        self.comboBox.setItemText(2, _translate("SearchTableDialog", "String", None))
        self.type_label.setText(_translate("SearchTableDialog", "Type", None))
        self.locations_button.setText(_translate("SearchTableDialog", "Select in Table", None))
        self.find_range_label.setText(_translate("SearchTableDialog", "Find Range", None))
        self.find_single_value_label.setText(_translate("SearchTableDialog", " Find Single Value", None))
        self.results_label.setText(_translate("SearchTableDialog", "Results", None))


if __name__ == "__main__":
    import sys
    app = QtGui.QApplication(sys.argv)
    SearchTableDialog = QtGui.QDialog()
    ui = Ui_SearchTableDialog()
    ui.setupUi(SearchTableDialog)
    SearchTableDialog.show()
    sys.exit(app.exec_())

