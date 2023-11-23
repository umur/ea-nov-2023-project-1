-- Inserting data into the event table
INSERT INTO event (name, date, description, category, userId)
VALUES
    ('Music Festival', '2023-04-15', 'Annual music festival featuring various artists.', 'Music', 1),
    ('Tech Conference', '2023-05-20', 'Explore the latest trends in technology and innovation.', 'Technology', 2),
    ('Charity Run', '2023-06-10', '5K charity run to support local causes.', 'Community', 3),
    ('Art Exhibition', '2023-07-08', 'Showcasing local and international artists.', 'Art', 4),
    ('Food Tasting', '2023-08-25', 'Experience a variety of cuisines from around the world.', 'Food', 5),
    ('Movie Night', '2023-09-12', 'Outdoor movie night under the stars.', 'Entertainment', 6),
    ('Science Fair', '2023-10-05', 'Interactive science exhibits and experiments.', 'Science', 7),
    ('Business Summit', '2023-11-18', 'Connect with industry leaders and discuss business strategies.', 'Business', 8),
    ('Fashion Show', '2023-12-03', 'Showcasing the latest trends in fashion.', 'Fashion', 9),
    ('Holiday Market', '2023-12-20', 'Festive market with holiday-themed goods.', 'Seasonal', 10);

-- Inserting data into the event_rsvp table
INSERT INTO event_rsvp (event_id, rsvp_user_id)
VALUES
    (1, 1), -- User 1 RSVPs to Music Festival
    (1, 2), -- User 2 RSVPs to Music Festival
    (1, 3), -- User 3 RSVPs to Music Festival
    (2, 4), -- User 4 RSVPs to Tech Conference
    (2, 5), -- User 5 RSVPs to Tech Conference
    (3, 1), -- User 1 RSVPs to Charity Run
    (3, 2), -- User 2 RSVPs to Charity Run
    (4, 3), -- User 3 RSVPs to Art Exhibition
    (4, 4), -- User 4 RSVPs to Art Exhibition
    (5, 5), -- User 5 RSVPs to Food Tasting
    (5, 1), -- User 1 RSVPs to Food Tasting
