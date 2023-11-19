-- Event Types --
INSERT INTO event_types (id, name)
VALUES (1, 'Reunions'),
        (2, 'Professional Networking Events'),
        (3, 'Workshops'),
        (4, 'Seminars');
-- Event Types --
-- Events --
INSERT INTO events (event_date, id, type_id, name, location)
VALUES -- Events of type 'Reunions'
        (
                '2023-11-10',
                1,
                1,
                'Reunion Event 1',
                'Location 1'
        ),
        (
                '2023-11-15',
                2,
                1,
                'Reunion Event 2',
                'Location 2'
        ),
        (
                '2023-11-15',
                3,
                1,
                'Reunion Event 3',
                'Location 3'
        ),
        (
                '2023-11-15',
                4,
                1,
                'Reunion Event 4',
                'Location 4'
        ),
        -- Events of type 'Professional Networking Events'
        (
                '2023-12-05',
                5,
                2,
                'Networking Event 1',
                'Location 5'
        ),
        (
                '2023-12-10',
                6,
                2,
                'Networking Event 2',
                'Location 6'
        ),
        (
                '2023-12-10',
                7,
                2,
                'Networking Event 3',
                'Location 7'
        ),
        (
                '2023-12-10',
                8,
                2,
                'Networking Event 4',
                'Location 8'
        ),
        -- Events of type 'Workshops'
        (
                '2023-11-30',
                9,
                3,
                'Workshop Event 1',
                'Location 9'
        ),
        (
                '2023-11-25',
                10,
                3,
                'Workshop Event 2',
                'Location 10'
        ),
        (
                '2023-11-25',
                11,
                3,
                'Workshop Event 3',
                'Location 11'
        ),
        (
                '2023-11-25',
                12,
                3,
                'Workshop Event 4',
                'Location 12'
        ),
        -- Events of type 'Seminars'
        (
                '2023-12-15',
                13,
                4,
                'Seminar Event 1',
                'Location 13'
        ),
        (
                '2023-11-20',
                14,
                4,
                'Seminar Event 2',
                'Location 14'
        ),
        (
                '2023-11-20',
                15,
                4,
                'Seminar Event 3',
                'Location 15'
        ),
        (
                '2023-11-20',
                16,
                4,
                'Seminar Event 4',
                'Location 16'
        );
-- Events --
-- User Organizing Events --
INSERT INTO users_organizing_events (event_id, organizer_id)
VALUES (3, 1),
        (4, 1),
        (7, 2),
        (11, 3),
        (15, 4),
        (5, 5),
        (6, 6),
        (12, 7),
        (16, 8),
        (1, 9),
        (10, 10);
-- User Organizing Events --
-- User Attending Events --
INSERT INTO users_attending_events (attendee_id, event_id)
VALUES (1, 1),
        (1, 5),
        (2, 2),
        (2, 6),
        (3, 3),
        (3, 7),
        (4, 4),
        (4, 8),
        (5, 9),
        (5, 13),
        (6, 10),
        (6, 14),
        (7, 11),
        (7, 15),
        (8, 12),
        (8, 16),
        (9, 5),
        (9, 14),
        (10, 6),
        (10, 15);
-- User Attending Events --
-- User Rsvp Events --
INSERT INTO users_rsvping_events (rsvper_Id, event_id)
VALUES (1, 1),
        (1, 5),
        (2, 2),
        (2, 6),
        (3, 3),
        (3, 7),
        (4, 4),
        (4, 8),
        (5, 9),
        (5, 13),
        (6, 10),
        (6, 14),
        (7, 11),
        (7, 15),
        (8, 12),
        (8, 16),
        (9, 5),
        (9, 14),
        (10, 6),
        (10, 15);
-- User Rsvp Events --