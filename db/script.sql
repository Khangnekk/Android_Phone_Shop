USE [master]
GO
/****** Object:  Database [PhoneStore]    Script Date: 11/8/2023 10:16:42 PM ******/
CREATE DATABASE [PhoneStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PhoneStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PhoneStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PhoneStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PhoneStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [PhoneStore] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PhoneStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PhoneStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PhoneStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PhoneStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PhoneStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PhoneStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [PhoneStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PhoneStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PhoneStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PhoneStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PhoneStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PhoneStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PhoneStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PhoneStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PhoneStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PhoneStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PhoneStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PhoneStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PhoneStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PhoneStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PhoneStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PhoneStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PhoneStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PhoneStore] SET RECOVERY FULL 
GO
ALTER DATABASE [PhoneStore] SET  MULTI_USER 
GO
ALTER DATABASE [PhoneStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PhoneStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PhoneStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PhoneStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PhoneStore] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PhoneStore] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'PhoneStore', N'ON'
GO
ALTER DATABASE [PhoneStore] SET QUERY_STORE = OFF
GO
USE [PhoneStore]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/8/2023 10:16:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](100) NULL,
	[full_name] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Messages]    Script Date: 11/8/2023 10:16:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Messages](
	[MessageID] [int] IDENTITY(1,1) NOT NULL,
	[SenderUsername] [nvarchar](50) NOT NULL,
	[ReceiverUsername] [nvarchar](50) NOT NULL,
	[MessageText] [nvarchar](max) NOT NULL,
	[MessageDateTime] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MessageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 11/8/2023 10:16:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NULL,
	[OrderDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 11/8/2023 10:16:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[PhoneID] [int] NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phone]    Script Date: 11/8/2023 10:16:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phone](
	[PhoneID] [int] IDENTITY(1,1) NOT NULL,
	[ModelName] [nvarchar](100) NOT NULL,
	[Manufacturer] [nvarchar](50) NULL,
	[Price] [decimal](10, 2) NULL,
	[Description] [nvarchar](max) NULL,
	[InStock] [bit] NULL,
	[ImageURL] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[PhoneID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'123', N'123', N'contact.khalgfk@gmail.com', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Admin', N'admin', N'Admin@gmail.com', N'Administrator')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'An', N'123', N'An@gmail.com', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Hoang', N'123', N'Khangnek@gmail.com', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Khang', N'123', N'Khang@gmail.com', N'Nguyen Luong Khang')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Khanghere', N'123', N'Khangnek@gmail.com', N'Khong Phai Khang')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Kiet', N'123', N'Kiet@gmail.com', N'Kiet')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'monkey', N'123', N'son@gmail.com', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'xyz', N'123', N'khangnlhe161660@fpt.edu.vn', NULL)
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([OrderID], [Username], [OrderDate]) VALUES (1, N'Khang', CAST(N'2023-10-25T00:00:00.000' AS DateTime))
INSERT [dbo].[Order] ([OrderID], [Username], [OrderDate]) VALUES (2, N'Khang', CAST(N'2023-10-26T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [PhoneID], [Quantity]) VALUES (1, 1, 14, 1)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [PhoneID], [Quantity]) VALUES (2, 2, 12, 1)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Phone] ON 

INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (1, N'Asus ROG Phone', N'Asus', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/asus_rog_phone_7_26029926987749e699f4fdbb3d412a4c.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (2, N'BPhone B60', N'Bphone', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/bphone_b60_472be80aa23f4577b5f8cda319078ef8.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (3, N'BPhone B86', N'Bphone', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/bphone-b86_c99829df2856415abd3947ff8f9e62d8.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (4, N'Huawei P50', N'Huawei', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/huawei_p50_e_b9941b5bb90d4dae980ab288ed4bc9f6.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (5, N'IPhone 5', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_5_fef021eebf2c45c4940723c812bb5b8e.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (6, N'Iphone 6', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_6_cbdec1a1378f4c23bfe0469cdecf1142.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (7, N'Iphone 7', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_7_2e13f9beed87474db08c77763d058596.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (8, N'Iphone 8', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_8_818baccc865e49baab4cbdbdbf01b13b.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (9, N'Iphone 11', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_11_pro_1c87bf6e6d26448ba9944911cfb1e3ff.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (10, N'Iphone 12', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_12_f6d82d869a4f42ada0b1bf96219a4e76.png')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (11, N'Iphone 14', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_14_e8f39dcba67245c5b4daf89592bb93a4.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (12, N'Iphone 14 Pro', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_14pro_067281a733944ffbb27819a7abb8dc31.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (13, N'IPhone 15', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_15_848be6a79e064163a12f587bd16ead21.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (14, N'Iphone 15 Pro', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_15pro_91be45d9032848998c57783d2b59ba97.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (15, N'IPhone SE', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_se_00d3f96478304d3a98114f4176e3dace.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (16, N'IPhone X', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/iphone_x_416dedc2540244ed80f85559abd20e33.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (17, N'Oppo A94', N'Oppo', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/oppo_a94_a14686ec64e847958c20c7247975ba5b.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (18, N'Realme Narzo 60', N'Realme', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/realme_narzo_60x_b4dd53ce34cc47778a193d431b1291f0.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (19, N'Redmi Note 10', N'Xiaomi', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/redmi-note10_11daf60495444740a201ada6e4d4b209.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (20, N'Samsung galaxy Note 10', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/samsung_note10_2a1631ed1d8b4a5e809867d8a79de336.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (22, N'Samsung galaxy Note20', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/sam-sung-note-20-ultra_85efff53d0bb4eaa9f93e8c9c5be42f2.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (23, N'Samsung galaxy s10 Plus', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/samsung-galaxy-s10-plus_104c63c2f2124e2382f73c8a79f25a38.png')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (24, N'Samsung galaxy Note 20 Ultra', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/samsung_note20_3535945e76454b6080790e886a77d64a.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (25, N'Vivo V2217 Y02', N'Vivo', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/vivo_v2217_y02_4795e42185e24ff38bade408b757b520.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (26, N'Vsmart Active 3', N'Vsmart', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/vsmart_active_3_642524e519054ffd94975526bb066a8b.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (27, N'Vsmart Live 4', N'Vsmart', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/vsmart_live_4_1ef3b6ae2cae4e2583c402fafbded5f2.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (28, N'Xiaomi Redimi K40', N'Xiaomi', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/xiaomi_redmi_k40_c29c46ca7a254a79afb4d7b0d6779f9d.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (29, N'Samsung Z Flip 3 5G', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/z-flip3-5g_8b3366b2c6a44c1eafe94fcf5d1db7ae.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (30, N'Samsung Z-Fold1', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/z-fold1_66f29e2a45614d199f3b4854e942fb44.jpg')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (31, N'Samsung Z-Fold4', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://file.hstatic.net/200000814795/file/z-fold4_994310bf58bd419bad076e2088cc47f4.png')
SET IDENTITY_INSERT [dbo].[Phone] OFF
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([Username])
REFERENCES [dbo].[Account] ([Username])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([PhoneID])
REFERENCES [dbo].[Phone] ([PhoneID])
GO
USE [master]
GO
ALTER DATABASE [PhoneStore] SET  READ_WRITE 
GO
