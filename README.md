# QuickCare – Microservices-Based Appointment Scheduling System

## Problem

Traditional hospitals and clinics often rely on outdated methods like phone calls or in-person visits to book appointments, leading to:

- Double bookings or conflicting time slots
- Long patient wait times and no-shows
- Limited visibility into doctor availability
- No central system to manage patient-doctor interactions

This results in inefficiencies for both patients and medical staff.

## Solution

**QuickCare** is a microservices-based backend system that allows:

-  Patients to book and cancel appointments online
-  Doctors to manage their availability and schedules
-  The system to validate overlapping slots and prevent conflicts
-  Notifications to be sent for confirmations and cancellations
-  Role-based secure access for different user types

This modular, scalable system can be deployed by hospitals or clinics of any size to improve operational efficiency.

## Architecture Overview

QuickCare is built using a Spring Boot microservices architecture that includes:

-  **Authentication & Role Management (JWT)**
-  **Eureka for service discovery**
-  **API Gateway for request routing**
-  **Central Config Server for shared properties**
-  **Separate services for Patients, Doctors, Appointments, and Notifications**
-  **Feign for inter-service communication**
-  **MySQL as the relational data store**

### Typical Flow:
1. Patient logs in → browses available doctors → selects a time slot
2. Appointment service checks:
   - Doctor availability
   - No overlapping appointments
3. On success, it saves the booking and triggers the Notification service

All services are independently deployable and communicate via Feign clients. 

