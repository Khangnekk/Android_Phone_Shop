USE [master]
GO
/****** Object:  Database [PhoneStore]    Script Date: 11/8/2023 2:07:14 PM ******/
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
/****** Object:  Table [dbo].[Account]    Script Date: 11/8/2023 2:07:15 PM ******/
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
/****** Object:  Table [dbo].[Messages]    Script Date: 11/8/2023 2:07:15 PM ******/
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
/****** Object:  Table [dbo].[Order]    Script Date: 11/8/2023 2:07:15 PM ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 11/8/2023 2:07:15 PM ******/
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
/****** Object:  Table [dbo].[Phone]    Script Date: 11/8/2023 2:07:15 PM ******/
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
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'', N'', N'', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'123', N'123', N'contact.khalgfk@gmail.com', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Admin', N'admin', N'Admin@gmail.com', N'Administrator')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'An', N'123', NULL, NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Hoang', N'123', N'Khangnek@gmail.com', NULL)
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Khang', N'123', N'Khang@gmail.com', N'Nguyen Luong Khang')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Khanghere', N'123', N'Khangnek@gmail.com', N'Khong Phai Khang')
INSERT [dbo].[Account] ([Username], [Password], [Email], [full_name]) VALUES (N'Kiet', N'123', NULL, NULL)
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

INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (1, N'Asus ROG Phone', N'Asus', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=10y4VwCC-RTN3eubu6jRp2MJzFgByZ-rJ
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (2, N'BPhone B60', N'Bphone', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1v_JfMyGMEOXXO1Iifp620rNORXr3eW1j
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (3, N'BPhone B86', N'Bphone', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1st484CfMH8j47OLIsjr3nyGjNavr2dvG
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (4, N'Huawei P50', N'Huawei', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=14uRwDEhdgtSH9BYsTn7RXLUpQ48SI9eL
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (5, N'IPhone 5', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1SmCgkLvnYQVQ_YCcMYWb8q0gl62_wuh6
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (6, N'Iphone 6', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=12xdhSEhBHuzPWQsH_epK5zB0Q8CY5Mox
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (7, N'Iphone 7', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1Ylj0ZW08lMjwv4DMXQAm7wcr0y45Y0JV
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (8, N'Iphone 8', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=17F7yjlXHGZcuQ3W-DBmDf8s-lndT67mw
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (9, N'Iphone 11', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1d5TAja4IkM0MSysHWxSt9C18ac23oDiu
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (10, N'Iphone 12', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1O1w30dlr_8Gha5MXVChiUfa_gEiBTcVg
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (11, N'Iphone 14', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1Gx78jG3hSzNgwuN5pO84F3v58Ma1jy3b
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (12, N'Iphone 14 Pro', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1RelPh4j50V6EXtuFCZVyMeBO6q2JqYRV
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (13, N'IPhone 15', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1RLZEqWuVZMJ__RUv_94UWjKs4wqo95AH
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (14, N'Iphone 15 Pro', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1aExGPXgbkpaMZ3wWHOn4C7GnP1fT921z
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (15, N'IPhone SE', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1cqGoanLA8THQt70QJ6XRMuAzQvmLOpqU
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (16, N'IPhone X', N'Apple', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1X_N1F8nLXmsNQcR1kfymWhXAqcazq_pq
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (17, N'Oppo A94', N'Oppo', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1As5LQbn6Up9leZxzv9PdXb1D_w3Y8lF9
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (18, N'Realme Narzo 60', N'Realme', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1UI7z3TN5MPZfFPUTKZUTIOJp1tBqTyzU
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (19, N'Redmi Note 10', N'Xiaomi', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1EHaWMJs5NjbNKWdchxjU6WOaVkfVX9Ew
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (20, N'Samsung galaxy Note 10', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1Z4ItbxzWYNH-YY9kDblei451PAX2z2Q3
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (22, N'Samsung galaxy Note20', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1L1BoHbFVl1jykKEa1DbHq2SYSoHxHoHj
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (23, N'Samsung galaxy s10 Plus', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1a-qduVpvuQjGgFUGgAm2UeKMx4NVlsT-
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (24, N'Samsung galaxy Note 20 Ultra', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1FltrTHv41FnhW_1zyENMKRCLMl5j4vhf
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (25, N'Vivo V2217 Y02', N'Vivo', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1CEUWxTvMcWFqnrUJyddThVBkgX7b6M-b
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (26, N'Vsmart Active 3', N'Vsmart', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1Ppm3rPNPWV6wNx4zOKmAzKMr9FAkIS2C
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (27, N'Vsmart Live 4', N'Vsmart', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1tNdh7TiX1GKucf4GJbB-kuN5kymZnwRz
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (28, N'Xiaomi Redimi K40', N'Xiaomi', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1lq2NkpX3p3yBdT3waro3k8vI_XyrDHGC
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (29, N'Samsung Z Flip 3 5G', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1D0XF6f9gWN17XZBVP6Zaaw_Ij3Iz7Yrp
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (30, N'Samsung Z-Fold1', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1lM3MKTPYxbcJ3fgaKNUGDpWdoIetwghj
')
INSERT [dbo].[Phone] ([PhoneID], [ModelName], [Manufacturer], [Price], [Description], [InStock], [ImageURL]) VALUES (31, N'Samsung Z-Fold4', N'Samsung', CAST(10000000.00 AS Decimal(10, 2)), NULL, 1, N'https://drive.google.com/uc?export=download&id=1SLwFvCd7AWKMiIDVq3EUwGKopahkkzQi
')
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
