package src;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.net.*;

import javafx.scene.control.Alert.AlertType;
import javafx.collections.*;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import src.common.*;
import src.common.exceptions.*;

class MyThread1 extends Thread
{
	Server s;
	VBox chatBox;
	private volatile boolean running;
	MyThread1(Server t, VBox v)
	{
		running = true;
		s = t;
		chatBox = v;
		start();
	}
	public void run()
	{
		String m = new String();
		while(running)
		{
			try{
				m = s.receiveMessage();
			}
			catch(IOException e)
			{
			}
			if(m != null)
			{
				String finalMessage = m;
				Platform.runLater(() -> {
					Label received = new Label(finalMessage);
					received.setStyle("-fx-background-color: #FFFFFF; " + // White background
                           "-fx-padding: 10px; " +
                           "-fx-background-radius: 15px; " +    // Rounded background for bubble effect
                           "-fx-border-radius: 15px; " +        // Rounded border corners
                           "-fx-border-color: lightgray; " +    // Light gray border
                           "-fx-border-width: 1px; " +          // Thin border width
                           "-fx-text-fill: #2B3A42;");          // Text color for readability

					HBox messagec = new HBox(received);
        			messagec.setPadding(new Insets(5));
        			messagec.setAlignment(Pos.CENTER_LEFT);

        			chatBox.getChildren().add(messagec);
				});
			}
			else
				break;
		}
	}
	void stopThread()
	{
		running = false;
	}
}

class MyThread2 extends Thread
{
	Client s;
	VBox chatBox;
	private volatile boolean running;
	MyThread2(Client t, VBox v)
	{
		running = true;
		s = t;
		chatBox = v;
		start();
	}
	public void run()
	{
		String m = new String();
		while(running)
		{
			try{
				m = s.receiveMessage();
			}
			catch(IOException e)
			{
			}
			if(m != null)
			{
				String finalMessage = m;
				Platform.runLater(() -> {
					Label received = new Label(finalMessage);
					received.setStyle("-fx-background-color: #FFFFFF; " + // White background
                           "-fx-padding: 10px; " +
                           "-fx-background-radius: 15px; " +    // Rounded background for bubble effect
                           "-fx-border-radius: 15px; " +        // Rounded border corners
                           "-fx-border-color: lightgray; " +    // Light gray border
                           "-fx-border-width: 1px; " +          // Thin border width
                           "-fx-text-fill: #2B3A42;");          // Text color for readability

					HBox messagec = new HBox(received);
        			messagec.setPadding(new Insets(5));
        			messagec.setAlignment(Pos.CENTER_LEFT);

        			chatBox.getChildren().add(messagec);
				});
			}
			else
				break;
		}
	}
	void stopThread()
	{
		running = false;
	}
}

public class HelloApplication extends Application {
    private VBox root;
    Client sc;
    Server ss;
    MyThread1 t1;
    VBox chatBox;
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hospital Management System");

        // Initialize the root variable
        root = new VBox();
        Scene newScene = new Scene(root, 800, 800);
        primaryStage.setScene(newScene);
        primaryStage.show();

        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.setStyle("-fx-background-color: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);");

        //root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, rgb(220, 235, 245) 3.3%, rgb(240, 245, 250) 109.6%);");
        root.setAlignment(Pos.TOP_CENTER);

        VBox headerBox = createHeader();
        root.getChildren().add(headerBox);

        // Welcome label
        Label welcome = new Label("Welcome! Let us know who you are");
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        welcome.setTextFill(Color.web("#00509E"));
        welcome.setAlignment(Pos.CENTER);
        root.getChildren().add(welcome);

        HBox buttonsBox = new HBox(10); // spacing of 10 between buttons
        buttonsBox.setAlignment(Pos.CENTER); // Center alignment for buttons
        Button doctor = new Button("Doctor");
        Button patient = new Button("Patient");
        doctor.setFont(Font.font("Arial", 16));
        patient.setFont(Font.font("Arial", 16));
        doctor.setStyle("-fx-background-color: #00308F; -fx-text-fill: #ffffff;");
        patient.setStyle("-fx-background-color: #00308F; -fx-text-fill: #ffffff;");

        // Add buttons to the buttonsBox
        buttonsBox.getChildren().addAll(doctor, patient);
        root.getChildren().add(buttonsBox);

        patient.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                showPatientPage(primaryStage);
            }
        });

        doctor.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                showDoctorPage(primaryStage);
            }
        });
    }

    private VBox createHeader() {
        HBox header = new HBox(10);
        header.setAlignment(Pos.TOP_CENTER);
        Image logoImage = new Image(/*"file:C:/Users/Dell/Downloads/*/"Caduceus.png");
        ImageView logo = new ImageView(logoImage);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);

        // Create and style the title label
        Label title = new Label("Hospital Management System");
        title.setFont(Font.font("Arial", 36));
        title.setTextFill(Color.web("#00308F"));

        // Add logo and title to the header HBox
        header.getChildren().addAll(logo, title);

        // Create a VBox for the header box
        VBox headerBox = new VBox(header);
        headerBox.setPadding(new Insets(20));
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00308F; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 10;");
        VBox.setMargin(headerBox, new Insets(0, 0, 150, 0));

        return headerBox; // Return the headerBox instead of header
    }

    private void showPatientPage(Stage primaryStage) {
        VBox patientPage = new VBox(20);
        patientPage.setAlignment(Pos.TOP_CENTER);
        patientPage.setPadding(new Insets(20));
        patientPage.setStyle("-fx-background-color: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);");

       // patientPage.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, rgb(220, 235, 245) 3.3%, rgb(240, 245, 250) 109.6%);");
        VBox headerBox = createHeader();
        patientPage.getChildren().add(headerBox);
        // Title for patient page
        Label patientHeader = new Label("Patient Services");
        patientHeader.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        patientHeader.setTextFill(Color.web("#00308F"));
        patientPage.getChildren().add(patientHeader);

        // Buttons for Patient Registration and Patient Consultation
        Button registrationButton = new Button("Patient Registration");
        Button consultationButton = new Button("Patient Consultation");
        Button communicationButton = new Button("Communication with doctor");
        registrationButton.setFont(Font.font("Arial", 16));
        consultationButton.setFont(Font.font("Arial", 16));
        communicationButton.setFont(Font.font("Arial", 16));
        registrationButton.setStyle("-fx-background-color: #00509E; -fx-text-fill: #ffffff;");
        consultationButton.setStyle("-fx-background-color: #00509E; -fx-text-fill: #ffffff;");
        communicationButton.setStyle("-fx-background-color: #00509E; -fx-text-fill: #ffffff;");

        // HBox for Patient buttons
        HBox patientButtons = new HBox(15);
        patientButtons.setAlignment(Pos.CENTER);
        patientButtons.getChildren().addAll(registrationButton, consultationButton, communicationButton);
        patientPage.getChildren().add(patientButtons);

        // New scene for patient page
        Scene patientScene = new Scene(patientPage, 800, 800);
        primaryStage.setScene(patientScene);


        // Event handlers for registration and consultation buttons
        registrationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                showPatientRegistrationPage(primaryStage);
            }
        });

        communicationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                showComPatPage(primaryStage);
            }
        });
//        Stage myStage=new Stage();
//        myStage=primaryStage;

        consultationButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae) {
               //Stage myStage=primaryStage;
                //System.out.println("Patient Consultation clicked.");
                showPatientConsultationPage(primaryStage);

            }

        });


    }

    private void showDoctorPage(Stage primaryStage) {
        VBox doctorPage = new VBox(20);
        doctorPage.setAlignment(Pos.TOP_CENTER);
        doctorPage.setPadding(new Insets(20));
        doctorPage.setStyle("-fx-background-color: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);");

       // patientPage.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, rgb(220, 235, 245) 3.3%, rgb(240, 245, 250) 109.6%);");
        VBox headerBox = createHeader();
        doctorPage.getChildren().add(headerBox);
        // Title for patient page
        Label doctorHeader = new Label("Doctor Functionalities");
        doctorHeader.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        doctorHeader.setTextFill(Color.web("#00308F"));
        doctorPage.getChildren().add(doctorHeader);

        // Buttons for Patient Registration and Patient Consultation
        Button communicationButton = new Button("Communication with patient portal");
        communicationButton.setFont(Font.font("Arial", 16));
        communicationButton.setStyle("-fx-background-color: #00509E; -fx-text-fill: #ffffff;");

        // HBox for Patient buttons
        HBox doctorButtons = new HBox(15);
        doctorButtons.setAlignment(Pos.CENTER);
        doctorButtons.getChildren().addAll(communicationButton);
        doctorPage.getChildren().add(doctorButtons);

        // New scene for patient page
        Scene doctorScene = new Scene(doctorPage, 800, 800);
        primaryStage.setScene(doctorScene);


        // Event handlers for registration and consultation buttons
        communicationButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                showComDocPage(primaryStage);
            }
        });
    }

    private void showComPatPage(Stage primaryStage)
    {
        Stage myStage = new Stage();
        myStage.setTitle("Patient communication portal");

        try{
			sc = new Client();
		}
		catch(IOException e)
		{
		}

        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setAlignment(Pos.TOP_LEFT);

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		Button send = new Button("Send");
        TextField sendText = new TextField();
		Button exit1 = new Button("Exit");
        HBox inputArea = new HBox(10, sendText, send, exit1);
        inputArea.setPadding(new Insets(10));
		inputArea.setAlignment(Pos.CENTER);

		HBox header = new HBox();
        Label headerLabel = new Label("Communication Portal: Patient's end");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-padding: 10px;");
        header.setStyle("-fx-background-color: #8BC34A;");
        header.getChildren().add(headerLabel);

        BorderPane rootNode = new BorderPane();
        rootNode.setCenter(scrollPane);
        rootNode.setBottom(inputArea);
		rootNode.setTop(header);

        send.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
				try{
					sc.sendMessage(sendText.getText());
				}
				catch(IOException e1)
				{
				}
				Label text = new Label();
				text.setText(sendText.getText());
				sendText.clear();
				text.setStyle("-fx-background-color: #DCF8C6; " +  // Light green background for chat bubble
                           "-fx-text-fill: #2A3A2F; " +         // Darker text color
                           "-fx-padding: 10px; " +              // Padding inside the label
                           "-fx-background-radius: 15px; " +    // Rounded background for bubble effect
                           "-fx-border-radius: 15px; " +        // Border rounding to match background
                           "-fx-border-width: 0; " +            // Remove the border width if desired
                           "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 2, 2);"); // Drop shadow for 3D effect

				HBox messageContainer = new HBox(text);
        		messageContainer.setPadding(new Insets(5));
        		messageContainer.setAlignment(Pos.CENTER_RIGHT);

        		chatBox.getChildren().add(messageContainer);

            }
        });

		MyThread2 t1 = new MyThread2(sc, chatBox);

		exit1.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
				try{
					sc.endClient();				
				}
				catch(IOException e1)
				{
				}
				t1.stopThread();
				myStage.close();
            }
        });

		Scene myScene = new Scene(rootNode);
		myStage.setScene(myScene);
		myStage.show();

    }

    private void showComDocPage(Stage primaryStage)
    {
        Stage myStage = new Stage();
        myStage.setTitle("Doctor communication portal");
		new Thread(() -> { try{
			ss = new Server();
            t1 = new MyThread1(ss, chatBox);
		}
		catch(IOException e)
		{
		}
        }).start();
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setAlignment(Pos.TOP_LEFT);

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		Button send = new Button("Send");
        TextField sendText = new TextField();
		Button exit1 = new Button("Exit");
        HBox inputArea = new HBox(10, sendText, send, exit1);
        inputArea.setPadding(new Insets(10));
		inputArea.setAlignment(Pos.CENTER);

		HBox header = new HBox();
        Label headerLabel = new Label("Communication Portal: Doctor's end");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-padding: 10px;");
        header.setStyle("-fx-background-color: #8BC34A;");
        header.getChildren().add(headerLabel);

        BorderPane rootNode = new BorderPane();
        rootNode.setCenter(scrollPane);
        rootNode.setBottom(inputArea);
		rootNode.setTop(header);

        send.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
				try{
					ss.sendMessage(sendText.getText());
				}
				catch(IOException e1)
				{
				}
				Label text = new Label();
				text.setText(sendText.getText());
				sendText.clear();
				text.setStyle("-fx-background-color: #DCF8C6; " +  // Light green background for chat bubble
                           "-fx-text-fill: #2A3A2F; " +         // Darker text color
                           "-fx-padding: 10px; " +              // Padding inside the label
                           "-fx-background-radius: 15px; " +    // Rounded background for bubble effect
                           "-fx-border-radius: 15px; " +        // Border rounding to match background
                           "-fx-border-width: 0; " +            // Remove the border width if desired
                           "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 2, 2);"); // Drop shadow for 3D effect

				HBox messageContainer = new HBox(text);
        		messageContainer.setPadding(new Insets(5));
        		messageContainer.setAlignment(Pos.CENTER_RIGHT);

        		chatBox.getChildren().add(messageContainer);

            }
        });


		exit1.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
				try{
					ss.endServer();				
				}
				catch(IOException e1)
				{
				}
				t1.stopThread();
				myStage.close();
            }
        });


		Scene myScene = new Scene(rootNode);
		myStage.setScene(myScene);
		myStage.show();

    }

   private void showPatientRegistrationPage(Stage primaryStage){
        Stage myStage=new Stage();
        myStage.setTitle("Patient Registration");
        GridPane root=new GridPane();
        Scene newScene=new Scene(root,600,600 );
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #f7fbff, #bbdefb);");
        root.setAlignment(Pos.CENTER);

        Label name=new Label("Name");
        TextField nameField=new TextField();
        Label age=new Label("Age");
        Spinner<Integer> ageSpinner = new Spinner<>(0, 120, 0);  // Range from 0 to 120 with default 25
        ageSpinner.setEditable(true);

        Label DOB=new Label("Date of Birth");
        DatePicker datePicker=new DatePicker();
        Label gender=new Label("Gender");
        ToggleGroup groupGender=new ToggleGroup();
        RadioButton male=new RadioButton("Male");
        male.setToggleGroup(groupGender);
        RadioButton female=new RadioButton("Female");
        female.setToggleGroup(groupGender);
        RadioButton other=new RadioButton("Other");
        other.setToggleGroup(groupGender);
        Label reg=new Label();

        Label address=new Label("Address");
        TextArea addressArea=new TextArea();
        addressArea.setPromptText("Enter your Address");
        addressArea.setWrapText(true);
        Label Contact=new Label("Contact number");
        TextField numberField=new TextField();

        Label BloodGroup=new Label("Blood Group");
        ComboBox<String> bloodGroupComboBox = new ComboBox<String>(FXCollections.observableArrayList("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"));
        bloodGroupComboBox.setPromptText("Select Blood Group");

        Label insurance=new Label("Do you have Insurance?");
        ToggleGroup Insrancecheck=new ToggleGroup();
        RadioButton yes=new RadioButton("Yes");
        yes.setToggleGroup(Insrancecheck);
        RadioButton no=new RadioButton("No");
        no.setToggleGroup(Insrancecheck);
        Button submit=new Button("Submit");
        root.add(name,0,0);
        root.add(nameField,1,0);
        root.add(age,0,1);
        root.add(ageSpinner, 1, 1);
        root.add(DOB, 0, 2);
        root.add(datePicker, 1, 2);
        HBox genderBox = new HBox(10); // 10 is the spacing between the radio buttons, adjust as needed
        genderBox.getChildren().addAll(male, female, other);


        root.add(gender, 0, 3);
        root.add(genderBox, 1, 3, 3, 1);


        root.add(address, 0, 4);
        root.add(addressArea, 1, 4, 3, 1);  // Spans across multiple columns

        root.add(Contact, 0, 5);
        root.add(numberField, 1, 5);

        root.add(BloodGroup, 0, 6);
        root.add(bloodGroupComboBox, 1, 6);

        root.add(insurance, 0, 7);
        root.add(yes, 1, 7);
        root.add(no, 2, 7);
        // root.add(submit,3,9);
        HBox submitBox = new HBox(submit);
        submitBox.setAlignment(Pos.CENTER);
        root.add(submitBox, 1, 9, 2, 1);
        submit.setStyle("-fx-background-color: #4DACFF;");
        Label registrationLabel = new Label(); // Create a label to display the registration number
        root.add(registrationLabel, 1, 8); // Add the label to the GridPane at row 8


        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String name = nameField.getText();
                Integer age = ageSpinner.getValue();
                LocalDate dob = datePicker.getValue();
                String genderValue = groupGender.getSelectedToggle() != null ? ((RadioButton) groupGender.getSelectedToggle()).getText() : null;
                String addressValue = addressArea.getText();
                String contactValue = numberField.getText();
                String insuranceValue = Insrancecheck.getSelectedToggle() != null ? ((RadioButton) Insrancecheck.getSelectedToggle()).getText() : null;
                boolean ins = insuranceValue == "Yes" ? true : false;


                // Check if any required field is empty
                String bloodGroupValue = bloodGroupComboBox.getValue();
                if (name.isEmpty() || dob == null || genderValue == null || addressValue.isEmpty() ||
                        contactValue.isEmpty() || bloodGroupComboBox.getValue() == null || insuranceValue == null) {
                    registrationLabel.setText("Registration Unsuccessful! Please enter all the details.");
                    registrationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                    registrationLabel.setTextFill(Color.RED); // Set the text color to red for error
                } else {
                    try {
                        if (numberField.getText().length() != 10) {
                            throw new InvalidContactNumberException("Invalid Contact Number");
                        }

                        // If all fields are filled, create a new patient registration object and show the registration number
                        Patient_registration probj = new Patient_registration(name, age, new DOB(dob.getDayOfMonth(), dob.getMonthValue(), dob.getYear()),
                                new Address(0, addressValue, 0), Long.parseLong(contactValue), bloodGroupValue, genderValue, ins); // Set insurance to false by default
                        registrationLabel.setText("Registration Successful! Your registration number is: " + probj.getRegistrationNo());
                        registrationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                        registrationLabel.setTextFill(Color.web("#003666"));
                        nameField.clear();
                        ageSpinner.getValueFactory().setValue(0); // Reset to default age
                        datePicker.setValue(null); // Clear date picker
                        groupGender.selectToggle(null); // Clear selected gender
                        addressArea.clear(); // Clear address area
                        numberField.clear(); // Clear contact number field
                        Insrancecheck.selectToggle(null);// Set a different color for successful registration

                    } catch (InvalidContactNumberException e) {
                        registrationLabel.setText(e.getMessage());
                        registrationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        registrationLabel.setTextFill(Color.RED);

                    }
                }
            }
        });
        myStage.setScene(newScene);
        myStage.show();




    }
    private void showPatientConsultationPage(Stage primaryStage){
        Patient_consultation p = new Patient_consultation();
        Stage myStage=new Stage();


        myStage.setTitle("Appointment portal");
        GridPane rootNode = new GridPane();
        rootNode.setHgap(10);
        rootNode.setVgap(10);
        rootNode.setAlignment(Pos.CENTER);

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #1565c0; -fx-padding: 10px;");
        header.setAlignment(Pos.CENTER);
        Label welcomeText = new Label("Patient Appointment Booking Portal");
        welcomeText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        header.getChildren().add(welcomeText);
        rootNode.add(header, 0, 0, 4, 1);

        HBox footer = new HBox();
        footer.setStyle("-fx-background-color: #1565c0; -fx-padding: 10px;");
        footer.setAlignment(Pos.CENTER);
        Label footerText = new Label("© 2024 HealthCare Portal");
        footerText.setStyle("-fx-text-fill: white;");
        footer.getChildren().add(footerText);
        rootNode.add(footer, 0, 17, 4, 1);

        rootNode.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #f7fbff, #bbdefb);");
        Button submit_details = new Button("Submit details");
        Label success = new Label("Success! Appointment booked");
        Label failed = new Label("Enter all the fields");
        Label date_select = new Label();
        Label time = new Label();
        Label fee = new Label();
        Label result = new Label();
        Label name = new Label("Enter your name: ");
        Label name_select = new Label();
        TextField name_field = new TextField();
        Alert aname = new Alert(AlertType.NONE);
        Button check_reg = new Button("Check registration");
        check_reg.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
                rootNode.getChildren().remove(failed);
                rootNode.getChildren().remove(name_select);
                rootNode.getChildren().remove(success);
                rootNode.getChildren().remove(result);
                rootNode.getChildren().remove(date_select);
                rootNode.getChildren().remove(time);
                rootNode.getChildren().remove(fee);
                DOB dateofbirth = new DOB(30, 9, 2005);
                Address addre = new Address(5, "Chennai", 600088);
                int x = 0;
                if(!name_field.getText().isEmpty()){
                    try{
                        x = p.checkRegistration(name_field.getText());
                    }
                    catch(NotRegisteredException e1)
                    {

                    }
                    if(x == 0){
                        rootNode.getChildren().remove(failed);
                        rootNode.getChildren().remove(name_select);
                        rootNode.getChildren().remove(success);
                        rootNode.getChildren().remove(result);
                        rootNode.getChildren().remove(date_select);
                        rootNode.getChildren().remove(time);
                        rootNode.getChildren().remove(fee);
                        submit_details.setDisable(true);
                        aname.setAlertType(AlertType.ERROR);
                        aname.setContentText("Sorry, " + name_field.getText() + " is not registered");
                        aname.show();
                    }
                    else{
                        submit_details.setDisable(false);
                        name_select.setTextFill(Color.RED);
                        name_select.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        name_select.setText("Patient registration number: " + p.getPatient().getRegistrationNo());
                        rootNode.add(name_select, 3, 3);
                        p.assignDiscount();
                        p.calculateAmt();
                        fee.setText("Appointment fee: Rs " + p.getAmt());
                    }
                }
                else{
                    submit_details.setDisable(false);
                    name_select.setTextFill(Color.RED);
                    name_select.setText("No name entered. Please enter the name of the patient.");
                    rootNode.add(name_select, 3, 4);
                }
            }
        });
        rootNode.add(name, 0, 4);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rootNode.add(name_field, 1, 4);
        rootNode.add(check_reg, 2, 4);
        Label dept = new Label("Choose the department you want to visit: ");
        Label dept_select = new Label();
        Button select = new Button("Confirm department");
        ComboBox<String> depts_avail;
        ObservableList<String> dept_types = FXCollections.observableArrayList("Cardiology", "Dermatology", "Gynaecology", "Oncology", "Neurology", "Orthopaedics", "Pediatrics");
        depts_avail = new ComboBox<String>(dept_types);
        select.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                if(depts_avail.getValue() != null){
                    dept_select.setTextFill(Color.BLACK);
                    dept_select.setText(depts_avail.getValue());
                }
                else{
                    dept_select.setTextFill(Color.RED);
                    dept_select.setText("No department selected. Select a department from the drop down.");
                }
            }
        });
        rootNode.add(dept, 0, 5);
        dept.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rootNode.add(depts_avail, 1, 5);
        rootNode.add(select, 2, 5);
        rootNode.add(dept_select, 3, 5);
        DatePicker doa = new DatePicker();
        Label date = new Label("Choose date of appointment: ");
        Button checkAvail = new Button("Check availability");
        Label confirm_date = new Label();
        checkAvail.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                int x = 0;
                LocalDate i;
                if(doa.getValue() != null){
                    i = doa.getValue();
                    DateTimeFormatter dfor = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                    date_select.setText("Appointment date: " + dfor.format(i));
                    Doctor testdoc = new Doctor("Dr. Senthil", "Pediatrics", "16");
                    Doctor testdoc2 = new Doctor("Dr. Hall", "Gynaecology", "14");
                    testdoc.setUnavailable(LocalDate.of(2024, 11, 1));
                    testdoc.setUnavailable(LocalDate.of(2024, 12, 1));
                    Patient_consultation.arr.insert(testdoc);
                    Patient_consultation.arr.insert(new Doctor("Dr. Smith", "Cardiology", "01"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Johnson", "Orthopaedics", "02"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Brown", "Pediatrics", "03"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Taylor", "Neurology", "04"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Anderson", "Dermatology", "05"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Martin", "Oncology", "06"));
                    Patient_consultation.arr.insert(new Doctor("Dr. White", "Gynaecology", "07"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Garcia", "Cardiology", "08"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Martinez", "Cardiology", "09"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Lee", "Neurology", "10"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Clark", "Orthopaedics", "11"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Lewis", "Pediatrics", "12"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Walker", "Dermatology", "13"));
                    Patient_consultation.arr.insert(testdoc2);
                    Patient_consultation.arr.insert(new Doctor("Dr. Hall", "Gynaecology", "14"));
                    Patient_consultation.arr.insert(new Doctor("Dr. Allen", "Oncology", "15"));
                    testdoc2.setUnavailable(LocalDate.of(2024, 11, 3));
                    if(depts_avail.getValue() != null){
                        try{
                            x = p.assign_Doc(i, depts_avail.getValue());
                        }
                        catch(DoctorUnavailableException dae)
                        {

                        }
                        if(x == 0){
			    rootNode.getChildren().remove(confirm_date);
                            rootNode.getChildren().remove(failed);
                            rootNode.getChildren().remove(success);
                            rootNode.getChildren().remove(result);
                            rootNode.getChildren().remove(date_select);
                            rootNode.getChildren().remove(time);
                            rootNode.getChildren().remove(fee);
                            submit_details.setDisable(true);
                            aname.setAlertType(AlertType.ERROR);
                            aname.setContentText("Sorry, no doctor is free in the entered slot");
                            aname.show();
                        }
                        else{
                            submit_details.setDisable(false);
                            confirm_date.setTextFill(Color.BLACK);
                            result.setText("Doctor: " + p.getDoctor().getDname());
                            confirm_date.setText(" Date of appointment confirmed");
                            rootNode.add(confirm_date, 3, 6);
                        }
                    }
                }
                else{
                    submit_details.setDisable(false);
                    confirm_date.setTextFill(Color.RED);
                    confirm_date.setText("No date selected. Select a date from the calendar.");
                    rootNode.add(confirm_date, 3, 6);
                }
            }
        });
        rootNode.add(date, 0, 6);
        date.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rootNode.add(doa, 1, 6);
        rootNode.add(checkAvail, 2, 6);
        Label time_select = new Label("Choose time of appointment: ");
        Label confirm_time = new Label();
        ComboBox<Integer> hour;
        ComboBox<Integer> min;
        ObservableList<Integer> hrposs = FXCollections.observableArrayList(10, 11, 12, 13, 14, 15, 16, 17);
        ObservableList<Integer> minposs = FXCollections.observableArrayList(00, 30);
        hour = new ComboBox<Integer>(hrposs);
        min = new ComboBox<Integer>(minposs);
        Button timeSubmit = new Button("Confirm time");
        timeSubmit.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                if(hour.getValue() != null && min.getValue() != null){
                    confirm_time.setTextFill(Color.BLACK);
                    time.setText("Appointment time: " + hour.getValue() + ":" + min.getValue() + " hours\n");
                    confirm_time.setText("Time of Appointment Confirmed");
                }
                else{
                    confirm_time.setTextFill(Color.RED);
                    confirm_time.setText("Invalid Time");
                }
            }
        });
        rootNode.add(time_select, 0, 7);
        time_select.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rootNode.add(hour, 1, 7);
        rootNode.add(min, 1, 8);
        rootNode.add(timeSubmit, 2, 7);
        rootNode.add(confirm_time, 3, 7);
        rootNode.add(submit_details, 1, 9);
        submit_details.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                int x = 0, a1, b1;
                if(!name_field.getText().isEmpty() && depts_avail.getValue() != null && doa != null && hour.getValue() != null && min.getValue() != null)
                {
                    rootNode.getChildren().remove(failed);
                    p.setDept(dept_select.getText());
                    p.setDOC(doa.getValue());
                    a1 = hour.getValue();
                    b1 = min.getValue();
                    p.setTime(LocalTime.of(a1, b1));
                    p.setCritical(false);
                    success.setTextFill(Color.BLUE);
                    result.setTextFill(Color.BLUE);
                    date_select.setTextFill(Color.BLUE);
                    time.setTextFill(Color.BLUE);
                    fee.setTextFill(Color.BLUE);
                    success.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    result.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    date_select.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    time.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    fee.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    rootNode.add(success, 1, 11);
                    rootNode.add(result, 1, 12);
                    rootNode.add(date_select, 1, 13);
                    rootNode.add(time, 1, 14);
                    rootNode.add(fee, 1, 15);
                    p.setAmt(500);
                }
                else{
                    rootNode.getChildren().remove(success);
                    rootNode.getChildren().remove(result);
                    rootNode.getChildren().remove(date_select);
                    rootNode.getChildren().remove(time);
                    rootNode.getChildren().remove(fee);
                    failed.setTextFill(Color.RED);
                    failed.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                    rootNode.add(failed, 1, 12);
                }
            }
        });
        Scene myScene = new Scene(rootNode);
        myStage.setScene(myScene);
        myStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
