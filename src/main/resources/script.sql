-- Tạo cơ sở dữ liệu
CREATE DATABASE WaterManagement;
USE WaterManagement;


-- Tạo bảng Role
CREATE TABLE Role (
    RoleID INT PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(100) NOT NULL,
    Description TEXT
);
-- Tạo bảng User

-- Tạo bảng Account
CREATE TABLE Account (
    AccountID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    RegistrationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);
CREATE TABLE User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(50) NOT NULL,
    IdentityNumber VARCHAR(12) NOT NULL UNIQUE,
    PhoneNumber VARCHAR(15) NOT NULL UNIQUE,
    Email VARCHAR(255) NOT NULL UNIQUE,
    IsActive BOOLEAN DEFAULT TRUE,
    ProfilePicture VARCHAR(255),
    AccountID INT,
    FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
);
-- Tạo bảng CustomerType

CREATE TABLE FacilityType (
    TypeID INT PRIMARY KEY AUTO_INCREMENT,
    TypeName VARCHAR(100) UNIQUE,
    CalculationMethod ENUM('Fixed', 'Tiered')
);

-- Tạo bảng Facility
CREATE TABLE Facility (
    FacilityID INT PRIMARY KEY AUTO_INCREMENT,
    Address VARCHAR(255),
    RegistrationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    OwnerId INT,
    FacilityTypeID INT,
    IsActive BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (OwnerId) REFERENCES User(UserID),
    FOREIGN KEY (FacilityTypeID) REFERENCES FacilityType(TypeID)
);


CREATE TABLE Position (
	PositionID INT PRIMARY KEY AUTO_INCREMENT,
    NamePosition VARCHAR(255),
    Description TEXT
);

-- Tạo bảng Employee
CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL UNIQUE,
    Address VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    AccountID INT,
    StartDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Image VARCHAR(255),
    FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
);



-- Tạo bảng LocationManager
CREATE TABLE LocationManager (
    ManagerID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    FacilityID INT,
    GrantedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(FacilityID)
);



-- Tạo bảng PricingTiers
CREATE TABLE PricingTiers (
    TierID INT PRIMARY KEY AUTO_INCREMENT,
    TypeID INT,
    MinUsage DECIMAL(10,2) NOT NULL,
    MaxUsage DECIMAL(10,2),
    PricePerM3 DECIMAL(10,2) NOT NULL CHECK (PricePerM3 > 0),
    FOREIGN KEY (TypeID) REFERENCES FacilityType(TypeID),
    CHECK (MaxUsage IS NULL OR MinUsage < MaxUsage)
);

-- Tạo bảng FixedPricing
CREATE TABLE FixedPricing (
    FixedPriceID INT PRIMARY KEY AUTO_INCREMENT,
    TypeID INT,
    PricePerM3 DECIMAL(10,2) NOT NULL CHECK (PricePerM3 > 0),
    FOREIGN KEY (TypeID) REFERENCES FacilityType(TypeID)
);

-- Tạo bảng WaterMeter
CREATE TABLE WaterMeter (
    WaterMeterID INT PRIMARY KEY AUTO_INCREMENT,
    SerialNumber VARCHAR(50) NOT NULL,
    FacilityID INT,
    InstallationDate DATE,
    IsActive BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (FacilityID) REFERENCES Facility(FacilityID)
);

-- Tạo bảng Invoice
CREATE TABLE Invoice (
    InvoiceID INT PRIMARY KEY AUTO_INCREMENT,
    PaymentDate DATETIME,
    TotalAmount DECIMAL(10,2) NOT NULL,
    Status ENUM('paid', 'unpaid', 'cancelled', 'overdue_penalty', 'suspended'),
    CreatedBy INT,
    PaidBy INT,
    FacilityID INT,
    CreationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    PenaltyFee DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (CreatedBy) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (PaidBy) REFERENCES User(UserID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(FacilityID)
);

-- Tạo bảng WaterMeterReading
CREATE TABLE WaterMeterReading (
    ReadingID INT PRIMARY KEY AUTO_INCREMENT,
    WaterMeterID INT,
    DateRecorded DATETIME DEFAULT CURRENT_TIMESTAMP,
    PreviousReading DECIMAL(10,2) NOT NULL,
    CurrentReading DECIMAL(10,2) NOT NULL,
    WaterUsage DECIMAL(10,2) NOT NULL,
    RecordedBy INT,
    InvoiceID Int,
    FOREIGN KEY (InvoiceID) REFERENCES Invoice( InvoiceID),
    FOREIGN KEY (WaterMeterID) REFERENCES WaterMeter(WaterMeterID),
    FOREIGN KEY (RecordedBy) REFERENCES Employee(EmployeeID),
    CHECK (CurrentReading >= PreviousReading),
    CHECK(WaterUsage = CurrentReading - PreviousReading)
);


-- Tạo bảng PaymentMethod
CREATE TABLE PaymentMethod (
    PaymentMethodID INT PRIMARY KEY AUTO_INCREMENT,
    MethodName VARCHAR(50),
    Description TEXT
);

-- Tạo bảng Transaction
CREATE TABLE Transaction (
    TransactionID INT PRIMARY KEY AUTO_INCREMENT,
    InvoiceID INT,
    PaymentMethodID INT,
    Amount DECIMAL(10,2) NOT NULL,
    Status ENUM('completed', 'failed', 'pending'),
    TransactionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    ReferenceCode VARCHAR(100),
    FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID),
    FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethod(PaymentMethodID)
);

-- Tạo bảng NotificationType
CREATE TABLE NotificationType (
    NotificationTypeID INT PRIMARY KEY AUTO_INCREMENT,
    TypeName VARCHAR(100),
    Description TEXT
);

-- Tạo bảng Notification
CREATE TABLE Notification (
    NotificationID INT PRIMARY KEY AUTO_INCREMENT,
    CreatedDate Datetime DEFAULT CURRENT_timestamp,
    SenderID INT,
    Title VARCHAR(255) NOT NULL,
    Content TEXT NOT NULL,
    NotificationTypeID INT,
    FOREIGN KEY (SenderID) REFERENCES Employee(EmployeeID),
    FOREIGN KEY (NotificationTypeID) REFERENCES NotificationType(NotificationTypeID)
);



-- Tạo bảng Notification_Facility
CREATE TABLE Notification_Facility (
    NotificationID INT,
    FacilityID INT,
    Status ENUM('Read', 'Unread'),
    FOREIGN KEY (NotificationID) REFERENCES Notification(NotificationID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(FacilityID),
    PRIMARY KEY (NotificationID, FacilityID)
);

-- Tạo bảng Permission
CREATE TABLE Permission (
    PermissionID INT PRIMARY KEY AUTO_INCREMENT,
    PermissionName VARCHAR(100) NOT NULL,
    Description TEXT
);

-- Tạo bảng Role_Permission
CREATE TABLE Role_Permission (
    RoleID INT,
    PermissionID INT,
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID),
    FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID),
    PRIMARY KEY (RoleID, PermissionID)
);

-- Tạo bảng News
CREATE TABLE News (
    NewsID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    Content TEXT NOT NULL,
    CreatedDate Datetime DEFAULT CURRENT_TIMESTAMP,
    CreatedBy INT,
    Status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (CreatedBy) REFERENCES Employee(EmployeeID)
);

-- Tạo bảng JoinRequest
CREATE TABLE JoinRequest (
    RequestID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    FacilityID INT,
    RequestDate Datetime DEFAULT CURRENT_TIMESTAMP,
    Note TEXT,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (FacilityID) REFERENCES Facility(FacilityID)
);



-- DELIMITER //
-- # waterUsage luôn bằng currentReading - previousReading
-- CREATE TRIGGER trg_CalculateWaterUsage_INSERT
-- BEFORE INSERT ON WaterMeterReading
-- FOR EACH ROW
-- BEGIN
--     SET NEW.WaterUsage = NEW.CurrentReading - NEW.PreviousReading;
-- END;

-- CREATE TRIGGER trg_CalculateWaterUsage_UPDATE
-- BEFORE UPDATE ON WaterMeterReading
-- FOR EACH ROW
-- BEGIN
--     SET NEW.WaterUsage = NEW.CurrentReading - NEW.PreviousReading;
-- END;

-- # Tự động cập nhật PaymentDate sau khi Status chuyển sang paid
-- CREATE TRIGGER trg_CheckPaymentDate
-- BEFORE UPDATE ON Invoice
-- FOR EACH ROW
-- BEGIN
--     IF NEW.Status = 'paid' AND OLD.Status != 'paid' THEN
--         SET NEW.PaymentDate = NOW();
--     ELSEIF NEW.Status != 'paid' THEN
--         SET NEW.PaymentDate = NULL;
--     END IF;
-- END;

-- # CustomerType chỉ có thể sử dụng một trong 2 phương thức là cố định hoặc không cố định
-- CREATE TRIGGER trg_CheckPricingMethod
-- BEFORE INSERT OR UPDATE ON CustomerType
-- FOR EACH ROW
-- BEGIN
--     DECLARE fixed_count INT;
--     DECLARE tiered_count INT;

--     SELECT COUNT(*) INTO fixed_count FROM FixedPricing WHERE TypeID = NEW.TypeID;
--     SELECT COUNT(*) INTO tiered_count FROM PricingTiers WHERE TypeID = NEW.TypeID;

--     IF fixed_count > 0 AND tiered_count > 0 THEN
--         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A CustomerType can only use one pricing method (Fixed or Tiered).';
--     END IF;
-- END;

-- # Một bản ghi WaterMeterReading chỉ thuộc về một hóa đơn
-- CREATE TRIGGER trg_CheckReadingInvoice
-- BEFORE INSERT ON Invoice_WaterMeterReading
-- FOR EACH ROW
-- BEGIN
--     DECLARE invoice_count INT;

--     SELECT COUNT(*) INTO invoice_count
--     FROM Invoice_WaterMeterReading
--     WHERE ReadingID = NEW.ReadingID;

--     IF invoice_count > 0 THEN
--         SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'A WaterMeterReading can only belong to one Invoice.';
--     END IF;
-- END;

-- # Tự động thêm vào LocationManager sau khi Facility được thêm vào
-- CREATE TRIGGER trg_AfterInsertFacility
-- AFTER INSERT ON Facility
-- FOR EACH ROW
-- BEGIN
--     -- Thêm bản ghi vào LocationManager
--     INSERT INTO LocationManager (UserID, FacilityID, GrantedDate, IsActive)
--     VALUES (NEW.OwnerId, NEW.FacilityID, CURRENT_DATE, TRUE);
-- END;
-- DELIMITER ;

-- Thêm dữ liệu mẫuaccount
-- Dữ liệu mẫu cho bảng Role
INSERT INTO Role (RoleName, Description) VALUES
('Admin', 'Quản trị viên hệ thống với toàn quyền'),
('Employee', 'Nhân viên quản lý và xử lý dữ liệu'),
('User', 'Người dùng thông thường');

-- Dữ liệu mẫu cho bảng Account
INSERT INTO Account (Username, Password, RegistrationDate, RoleID) VALUES
('admin01', 'hashed_password1', '2025-01-01 10:00:00', 1),
('employee01', 'hashed_password2', '2025-01-02 09:00:00', 2),
('user01', 'hashed_password3', '2025-01-03 15:00:00', 3),
('user02', 'hashed_password4', '2025-01-04 12:00:00', 3);

-- Dữ liệu mẫu cho bảng User
INSERT INTO User (FullName, IdentityNumber, PhoneNumber, Email, IsActive, ProfilePicture, AccountID) VALUES
('Nguyen Van A', '123456789012', '0901234567', 'nguyenvana@example.com', TRUE, 'profile1.jpg', 1),
('Tran Thi B', '987654321098', '0912345678', 'tranthib@example.com', TRUE, 'profile2.jpg', 2),
('Le Van C', '456789123456', '0923456789', 'levanc@example.com', TRUE, 'profile3.jpg', 3),
('Pham Thi D', '654321987654', '0934567890', 'phamthid@example.com', TRUE, 'profile4.jpg', 4);

-- Dữ liệu mẫu cho bảng FacilityType
INSERT INTO FacilityType (TypeName, CalculationMethod) VALUES
('Residential', 'Tiered'),
('Commercial', 'Fixed');

-- Dữ liệu mẫu cho bảng Facility
INSERT INTO Facility (Address, RegistrationDate, OwnerId, FacilityTypeID, IsActive) VALUES
('123 Đường Láng, Hà Nội', '2025-02-01 08:00:00', 3, 1, TRUE),
('456 Lê Lợi, TP.HCM', '2025-02-02 10:00:00', 4, 2, TRUE);

-- Dữ liệu mẫu cho bảng Position
INSERT INTO Position (NamePosition, Description) VALUES
('Quản lý', 'Quản lý cơ sở và nhân viên'),
('Kỹ thuật viên', 'Hỗ trợ kỹ thuật và bảo trì');

-- Dữ liệu mẫu cho bảng Employee
INSERT INTO Employee (FullName, PhoneNumber, Address, Email, AccountID, StartDate, Image) VALUES
('Tran Thi B', '0912345678', '789 Trần Phú, Hà Nội', 'tranthib@example.com', 2, '2025-01-02 09:00:00', 'emp1.jpg');

-- Dữ liệu mẫu cho bảng LocationManager
INSERT INTO LocationManager (UserID, FacilityID, GrantedDate) VALUES
(1, 1, '2025-02-01 09:00:00'),
(3, 2, '2025-02-02 11:00:00');

-- Dữ liệu mẫu cho bảng PricingTiers
INSERT INTO PricingTiers (TypeID, MinUsage, MaxUsage, PricePerM3) VALUES
(1, 0.00, 10.00, 5000.00),
(1, 10.00, 20.00, 7000.00),
(1, 20.00, NULL, 10000.00);

-- Dữ liệu mẫu cho bảng FixedPricing
INSERT INTO FixedPricing (TypeID, PricePerM3) VALUES
(2, 12000.00);

-- Dữ liệu mẫu cho bảng WaterMeter
INSERT INTO WaterMeter (SerialNumber, FacilityID, InstallationDate, IsActive) VALUES
('WM001', 1, '2025-02-01', TRUE),
('WM002', 2, '2025-02-02', TRUE);

-- Dữ liệu mẫu cho bảng Invoice
INSERT INTO Invoice (PaymentDate, TotalAmount, Status, CreatedBy, PaidBy, FacilityID, CreationDate, PenaltyFee) VALUES
(NULL, 75000.00, 'unpaid', 1, 3, 1, '2025-03-01 10:00:00', 0.00),
('2025-03-05 15:00:00', 120000.00, 'paid', 1, 4, 2, '2025-03-02 12:00:00', 0.00);

-- Dữ liệu mẫu cho bảng WaterMeterReading
INSERT INTO WaterMeterReading (WaterMeterID, DateRecorded, PreviousReading, CurrentReading, WaterUsage, RecordedBy, InvoiceID) VALUES
(1, '2025-03-01 09:00:00', 0.00, 15.00, 15.00, 1, 1),
(2, '2025-03-02 11:00:00', 0.00, 10.00, 10.00, 1, 2);

-- Dữ liệu mẫu cho bảng PaymentMethod
INSERT INTO PaymentMethod (MethodName, Description) VALUES
('Cash', 'Thanh toán bằng tiền mặt'),
('Bank Card', 'Thanh toán qua thẻ ngân hàng');

-- Dữ liệu mẫu cho bảng Transaction
INSERT INTO Transaction (InvoiceID, PaymentMethodID, Amount, Status, TransactionDate, ReferenceCode) VALUES
(2, 2, 120000.00, 'completed', '2025-03-05 15:00:00', 'TXN001');

-- Dữ liệu mẫu cho bảng NotificationType
INSERT INTO NotificationType (TypeName, Description) VALUES
('Hóa đơn', 'Thông báo về hóa đơn'),
('Cảnh báo', 'Thông báo khẩn cấp');

-- Dữ liệu mẫu cho bảng Notification
INSERT INTO Notification (CreatedDate, SenderID, Title, Content, NotificationTypeID) VALUES
('2025-03-01 08:00:00', 1, 'Hóa đơn tháng 3', 'Hóa đơn nước tháng 3 đã được tạo.', 1),
('2025-03-02 09:00:00', 1, 'Cảnh báo bảo trì', 'Cần bảo trì đồng hồ nước WM001.', 2);

-- Dữ liệu mẫu cho bảng Notification_Facility
INSERT INTO Notification_Facility (NotificationID, FacilityID, Status) VALUES
(1, 1, 'Unread'),
(2, 1, 'Read');

-- Dữ liệu mẫu cho bảng Permission
INSERT INTO Permission (PermissionName, Description) VALUES
('Manage_Users', 'Quản lý người dùng'),
('View_Reports', 'Xem báo cáo');

-- Dữ liệu mẫu cho bảng Role_Permission
INSERT INTO Role_Permission (RoleID, PermissionID) VALUES
(1, 1),
(1, 2),
(2, 2);

-- Dữ liệu mẫu cho bảng News
INSERT INTO News (Title, Content, CreatedDate, CreatedBy, Status) VALUES
('Cập nhật giá nước', 'Giá nước mới sẽ áp dụng từ tháng 4.', '2025-03-01 07:00:00', 1, TRUE);

-- Dữ liệu mẫu cho bảng JoinRequest
INSERT INTO JoinRequest (UserID, FacilityID, RequestDate, Note) VALUES
(4, 1, '2025-03-03 14:00:00', 'Yêu cầu tham gia cơ sở 123 Đường Láng');


