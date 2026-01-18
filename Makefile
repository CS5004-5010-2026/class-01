# Makefile for Digital Clock Python project

.PHONY: help install test coverage clean lint format type-check run demo all

# Default target
help:
	@echo "Digital Clock - Python Development Commands"
	@echo "==========================================="
	@echo ""
	@echo "Setup:"
	@echo "  make install      Install dependencies"
	@echo "  make venv         Create virtual environment"
	@echo ""
	@echo "Testing:"
	@echo "  make test         Run all tests"
	@echo "  make coverage     Run tests with coverage report"
	@echo "  make test-watch   Run tests in watch mode"
	@echo ""
	@echo "Code Quality:"
	@echo "  make lint         Run pylint"
	@echo "  make format       Format code with black"
	@echo "  make type-check   Run mypy type checking"
	@echo "  make quality      Run all quality checks"
	@echo ""
	@echo "Running:"
	@echo "  make run          Run the demo"
	@echo "  make demo         Run the demo (alias)"
	@echo ""
	@echo "Cleanup:"
	@echo "  make clean        Remove generated files"
	@echo "  make clean-all    Remove all generated files and venv"
	@echo ""
	@echo "All-in-one:"
	@echo "  make all          Install, test, and check quality"

# Setup
venv:
	python3 -m venv venv
	@echo "Virtual environment created. Activate with:"
	@echo "  source venv/bin/activate  (macOS/Linux)"
	@echo "  venv\\Scripts\\activate     (Windows)"

install:
	pip install -r requirements.txt

# Testing
test:
	pytest

coverage:
	pytest --cov=DigitalClock --cov-report=term-missing --cov-report=html
	@echo ""
	@echo "Coverage report generated in htmlcov/index.html"

test-watch:
	pytest-watch

# Code Quality
lint:
	pylint DigitalClock.py test_digital_clock.py

format:
	black DigitalClock.py test_digital_clock.py

type-check:
	mypy DigitalClock.py

quality: lint type-check
	@echo "All quality checks passed!"

# Running
run: demo

demo:
	python DigitalClock.py

# Cleanup
clean:
	rm -rf __pycache__
	rm -rf .pytest_cache
	rm -rf .coverage
	rm -rf htmlcov
	rm -rf .mypy_cache
	find . -type f -name "*.pyc" -delete
	find . -type d -name "__pycache__" -delete

clean-all: clean
	rm -rf venv

# All-in-one
all: install test quality
	@echo ""
	@echo "✓ All checks passed!"
