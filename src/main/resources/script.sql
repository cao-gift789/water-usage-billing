- Tạo cơ sở dữ liệu
CREATE DATABASE WaterManagement;
USE WaterManagement;



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
INSERT INTO Facility_Type (TypeName, CalculationMethod) VALUES
('Residential', 'Tiered'),
('Commercial', 'Fixed');

-- Dữ liệu mẫu cho bảng Facility
INSERT INTO Facility (Address, RegistrationDate, OwnerId, FacilityTypeID, IsActive) VALUES
('123 Đường Láng, Hà Nội', '2025-02-01 08:00:00', 3, 1, TRUE),
('456 Lê Lợi, TP.HCM', '2025-02-02 10:00:00', 4, 2, TRUE);


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

-- Procedure truc
-- Quản lý hộ gia đình ( Xem, Thêm hộ gia đình, dừng hoạt động hộ gia đình).

--Xem
--DELIMITER $$
--
--CREATE PROCEDURE sp_get_all_facilities()
--BEGIN
--    SELECT 
--        f.FacilityID,
--        f.Address,
--        f.RegistrationDate,
--        f.IsActive,
--        u.FullName AS OwnerName,
--        u.UserID AS OwnerID
--    FROM facility f
--    JOIN user u ON f.OwnerId = u.UserID;
--END $$
--
--DELIMITER ;


--Thêm hộ gia đình
--DELIMITER $$
--
--CREATE PROCEDURE sp_add_facility(
--    IN p_address VARCHAR(255),
--    IN p_registrationDate DATETIME(6),
--    IN p_ownerId INT,
--    IN p_facilityTypeId INT
--)
--BEGIN
--    INSERT INTO facility (Address, RegistrationDate, OwnerId, FacilityTypeID, IsActive)
--    VALUES (p_address, p_registrationDate, p_ownerId, p_facilityTypeId, 1);
--END $$
--
--DELIMITER ;


--dừng hoạt động hộ gia đình
--DELIMITER $$
--
--CREATE PROCEDURE sp_deactivate_facility(
--    IN p_facilityId INT
--)
--BEGIN
--    UPDATE facility
--    SET IsActive = 0
--    WHERE FacilityID = p_facilityId;
--END $$
--
--DELIMITER ;


--Quản lý tin tức (Xem, xóa, sửa, thêm):

--Xem
--DELIMITER $$
--
--CREATE PROCEDURE sp_get_all_news()
--BEGIN
--    SELECT NewsID, Title, Content, CreatedDate, CreatedBy
--    FROM news
--    WHERE Status = 1;
--END $$
--
--DELIMITER ;



--Thêm 
--DELIMITER $$
--
--CREATE PROCEDURE sp_add_news(
--    IN p_title VARCHAR(100),
--    IN p_content TEXT,
--    IN p_createdBy INT
--)
--BEGIN
--    INSERT INTO news (Title, Content, CreatedDate, CreatedBy, Status)
--    VALUES (p_title, p_content, NOW(), p_createdBy, 1);
--END $$
--
--DELIMITER ;



--Suẳ
--DELIMITER $$
--
--CREATE PROCEDURE sp_update_news(
--    IN p_newsId INT,
--    IN p_title VARCHAR(100),
--    IN p_content TEXT
--)
--BEGIN
--    UPDATE news
--    SET Title = p_title,
--        Content = p_content
--    WHERE NewsID = p_newsId AND Status = 1;
--END $$
--
--DELIMITER ;



--xóa
--DELIMITER $$
--
--CREATE PROCEDURE sp_delete_news(IN p_newsId INT)
--BEGIN
--    UPDATE news
--    SET Status = 0
--    WHERE NewsID = p_newsId;
--END $$
--
--DELIMITER ;


-- Nhân viên đang làm, tối sẽ cập nhật sớm cho ae
